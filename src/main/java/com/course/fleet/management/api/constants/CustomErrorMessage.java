package com.course.fleet.management.api.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomErrorMessage {

  public static final String USER_ALREADY_EXISTS = "The user already exists";
  public static final String USER_NOT_FOUND = "The user has not been found";
  public static final String BAD_REQUEST = "The given resource is malformed";
}
