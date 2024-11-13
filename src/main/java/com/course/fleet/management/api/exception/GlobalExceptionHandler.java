package com.course.fleet.management.api.exception;

import com.course.fleet.management.api.dto.response.CustomErrorResponseDTO;
import com.course.fleet.management.api.exception.customExceptions.CustomAbstractException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
