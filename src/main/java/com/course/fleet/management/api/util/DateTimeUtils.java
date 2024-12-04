package com.course.fleet.management.api.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeUtils {

  public static LocalDateTime getDateAtStartOfDay(String date) {
    LocalDate dateParsed = LocalDate.parse(date);
    return dateParsed.atStartOfDay();
  }

  public static LocalDateTime getDateAtEndOfDay(String date) {
    LocalDate dateParsed = LocalDate.parse(date);
    return dateParsed.atTime(LocalTime.MAX);
  }
}
