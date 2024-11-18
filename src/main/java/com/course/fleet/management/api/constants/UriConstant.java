package com.course.fleet.management.api.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UriConstant {
  public static final String USER_BASE_URL = "/user";
  public static final String USER_GET_ALL = "/all";
  public static final String USER_BY_ID = "/{id}";
  public static final String USER_UPDATE = "/{id}";
  public static final String USER_UPDATE_ATTRIBUTE = "/{id}";
  public static final String USER_DELETE = "/{id}";
}
