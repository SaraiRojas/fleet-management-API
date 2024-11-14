package com.course.fleet.management.api.exception.customExceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends CustomAbstractException {

  public BadRequestException(String errorMessage) {
    super(errorMessage);
  }

  public BadRequestException(String errorMessage, String field, String value) {
    super(errorMessage, field, value);
  }
}
