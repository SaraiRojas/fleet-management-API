package com.course.fleet.management.api.exception.customExceptions;

import lombok.Getter;

@Getter
public class InvalidFormatException extends CustomAbstractException {
  public InvalidFormatException(String errorMessage) {
    super(errorMessage);
  }

  public InvalidFormatException(String errorMessage, String field, String value) {
    super(errorMessage, field, value);
  }
}
