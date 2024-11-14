package com.course.fleet.management.api.exception.customExceptions;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends CustomAbstractException {

  public UserAlreadyExistsException(String errorMessage) {
    super(errorMessage);
  }

  public UserAlreadyExistsException(String errorMessage, String field, String value) {
    super(errorMessage, field, value);
  }
}
