package by.chess.bot.service.parser.faculty_page.misc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class WeekPeriodServiceFactory {
  public WeekPeriodService getDateUtils() {
    LocalDate date = ZonedDateTime.now(ZoneId.of("Europe/Minsk")).toLocalDate();
    if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
      return new WeekPeriodService(date.plusDays(1));
    }
    return new WeekPeriodService(date);
  }
}
