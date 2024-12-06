package com.course.fleet.management.api.exception.customExceptions;

import lombok.Getter;

@Getter
public class TrajectoryNotFoundException extends CustomAbstractException {

  public TrajectoryNotFoundException(String customErrorMessage) {
    super(customErrorMessage);
  }
}
