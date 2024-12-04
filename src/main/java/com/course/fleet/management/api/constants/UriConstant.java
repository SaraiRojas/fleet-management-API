package com.course.fleet.management.api.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UriConstant {

  // User
  public static final String USER_BASE_URL = "/user";
  public static final String USER_GET_ALL = "/all";
  public static final String USER_BY_ID = "/{id}";
  public static final String USER_UPDATE = "/{id}";
  public static final String USER_UPDATE_ATTRIBUTE = "/{id}";
  public static final String USER_DELETE = "/{id}";

  // Taxi

  public static final String TAXI_BASE_URL = "/taxi";
  public static final String TAXI_GET_ALL = "/all";
  public static final String TAXI_GET_BY_PLATE = "/{plate}";

  // Trajectory

  public static final String TRAJECTORY_BASE_URL = "/trajectory";

  public static final String TAXI_TRAJECTORY = "/{taxiId}";

  public static final String LATEST_TRAJECTORIES = "/latest";
}
