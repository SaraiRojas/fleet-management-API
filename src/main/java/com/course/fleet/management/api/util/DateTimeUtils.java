package com.course.fleet.management.api.util;

import static com.course.fleet.management.api.constants.CustomErrorMessage.INVALID_DATE_FORMAT;

import com.course.fleet.management.api.exception.customExceptions.InvalidFormatException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DateTimeUtils {

  public static void ValidateDateFormat(String date) {
    try {
      LocalDate.parse(date);
    } catch (DateTimeParseException e) {
      throw new InvalidFormatException(INVALID_DATE_FORMAT, "date", date);
    }
  }

  public static LocalDateTime getDateAtStartOfDay(String date) {
    final LocalDate dateParsed = LocalDate.parse(date);
    return dateParsed.atStartOfDay();
  }

  public static LocalDateTime getDateAtEndOfDay(String date) {
    final LocalDate dateParsed = LocalDate.parse(date);
    return dateParsed.atTime(23, 59, 59);
  }
}
