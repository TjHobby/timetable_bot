package by.chess.bot.service.parser.faculty_page.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {
  private static final int WORKING_DAYS = 6;
  private static final String DATE_PATTERN = "d MMMM";
  private final LocalDate today;

  public DateUtils(LocalDate date) {
    this.today = date;
  }

  public String getLastDayOfWeekString() {
    return formatDateToCyrillic(getLastWorkingDayOfWeek());
  }

  public String getFirstDayOfWeekString() {
    return formatDateToCyrillic(getFirstDayOfWeek());
  }

  private LocalDate getLastWorkingDayOfWeek() {
    return today.plusDays(WORKING_DAYS - today.getDayOfWeek().getValue());
  }

  private LocalDate getFirstDayOfWeek() {
    long dayOffset = today.getDayOfWeek().getValue() - 1;
    return today.minusDays(dayOffset);
  }

  private String formatDateToCyrillic(LocalDate date) {
    return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN).withLocale(new Locale("ru")));
  }
}
