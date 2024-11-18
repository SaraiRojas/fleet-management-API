package com.course.fleet.management.api.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.course.fleet.management.api.dto.response.CustomErrorResponseDTO;
import com.course.fleet.management.api.exception.customExceptions.CustomAbstractException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomAbstractException.class)
  public ResponseEntity<CustomErrorResponseDTO> handleCustomAbstractException(
      HttpServletRequest request, CustomAbstractException ex) {
    final CustomErrorResponseDTO customErrorResponseDTO =
        CustomErrorResponseDTO.builder()
            .statusCode(ex.getStatus())
            .errorMessage(ex.getCustomErrorMessage())
            .field(ex.getField())
            .value(ex.getValue())
            .path(request.getRequestURI())
            .build();

    return ResponseEntity.status(ex.getStatus()).body(customErrorResponseDTO);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<CustomErrorResponseDTO> handleMethodArgumentNotValidException(
      HttpServletRequest request, MethodArgumentNotValidException ex) {
    final BindingResult bindingResult = ex.getBindingResult();

    final CustomErrorResponseDTO customErrorResponseDTO =
        CustomErrorResponseDTO.builder()
            .statusCode(BAD_REQUEST)
            .path(request.getRequestURI())
            .build();

    final FieldError firstFieldError = bindingResult.getFieldErrors().get(0);

    customErrorResponseDTO.setErrorMessage(firstFieldError.getDefaultMessage());
    customErrorResponseDTO.setField(firstFieldError.getField());
    customErrorResponseDTO.setValue(getJakartaValue(firstFieldError));

    return ResponseEntity.status(customErrorResponseDTO.getStatusCode())
        .body(customErrorResponseDTO);
  }

  protected String getJakartaValue(FieldError fieldError) {
    return (fieldError.getRejectedValue() == null)
        ? "null"
        : (fieldError.getRejectedValue().toString().isEmpty()
            ? "blank"
            : fieldError.getRejectedValue().toString());
  }
}
