package com.course.fleet.management.api.exception.customExceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@SuppressWarnings("rawtypes")
public abstract class CustomAbstractException extends RuntimeException {

  private final String customErrorMessage;

  private final HttpStatus status;

  private final String field;

  private final String value;

  private static final Map<HttpStatus, List<Class>> statusMap = new HashMap<>();

  static {
    statusMap.put(NOT_FOUND, List.of(UserNotFoundException.class));
    statusMap.put(
        BAD_REQUEST,
        List.of(
            UserAlreadyExistsException.class,
            BadRequestException.class,
            TrajectoryNotFoundException.class));
  }

  public CustomAbstractException(String customErrorMessage) {
    super(customErrorMessage);
    this.customErrorMessage = customErrorMessage;
    this.field = null;
    this.value = null;
    this.status = getHttpStatus();
  }

  public CustomAbstractException(String customErrorMessage, String field, String value) {
    super(customErrorMessage);
    this.customErrorMessage = customErrorMessage;
    this.field = field;
    this.value = value;
    this.status = getHttpStatus();
  }

  public HttpStatus getHttpStatus() {
    for (Map.Entry<HttpStatus, List<Class>> entry : statusMap.entrySet()) {
      for (Class _class : entry.getValue()) {
        if (_class.isInstance(this)) {
          return entry.getKey();
        }
      }
    }
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }
}
