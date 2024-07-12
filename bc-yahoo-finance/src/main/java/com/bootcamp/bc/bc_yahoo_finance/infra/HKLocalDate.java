package com.bootcamp.bc.bc_yahoo_finance.infra;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class HKLocalDate {

  private static final Set<LocalDate> holidays2024 = new HashSet<>(Set.of(//
      LocalDate.of(2024, 1, 1), // The first day of January
      LocalDate.of(2024, 2, 10), // Lunar New Year's Day
      LocalDate.of(2024, 2, 12), // The third day of Lunar New Year
      LocalDate.of(2024, 2, 13), // The fourth day of Lunar New Year
      LocalDate.of(2024, 3, 29), // Good Friday
      LocalDate.of(2024, 3, 30), // The day following Good Friday
      LocalDate.of(2024, 4, 1), // Easter Monday
      LocalDate.of(2024, 4, 4), // Ching Ming Festival
      LocalDate.of(2024, 5, 1), // Labour Day
      LocalDate.of(2024, 5, 15), // The Birthday of the Buddha
      LocalDate.of(2024, 6, 10), // Tuen Ng Festival
      LocalDate.of(2024, 7, 1), // Hong Kong Special Administrative Region Establishment Day
      LocalDate.of(2024, 9, 18), // The day following the Chinese Mid-Autumn Festival
      LocalDate.of(2024, 10, 1), // National Day
      LocalDate.of(2024, 10, 11), // Chung Yeung Festival
      LocalDate.of(2024, 12, 25), // Christmas Day
      LocalDate.of(2024, 12, 26) // The first weekday after Christmas Day
  ));

  public static boolean isPublicHoliday(LocalDate date) {
    return holidays2024.contains(date);
  }

}
