package com.bootcamp.bc.bc_yahoo_finance.infra;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TimeConverter {

  public static Long dateAndTime(LocalDateTime dateTime) {
    return dateTime.toEpochSecond(ZoneOffset.UTC);
  }

  public static String dateAndTime(Long unixTime) {
    return LocalDateTime
        .ofInstant(Instant.ofEpochSecond(unixTime), ZoneId.of("Asia/Hong_Kong"))
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  public static String date(Long unixTime) {
    return LocalDateTime
        .ofInstant(Instant.ofEpochSecond(unixTime), ZoneId.of("Asia/Hong_Kong"))
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  public static String date(LocalDate date) {
    return date.atStartOfDay(ZoneId.of("Asia/Hong_Kong"))
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

}
