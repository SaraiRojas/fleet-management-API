package com.course.fleet.management.api.exception.customExceptions;

import lombok.Getter;

@Getter
public class UserNotFoundException extends CustomAbstractException {

  public UserNotFoundException(String customErrorMessage) {
    super(customErrorMessage);
  }

  public UserNotFoundException(String errorMessage, String field, String value) {
    super(errorMessage, field, value);
  }
}
