package by.chess.bot.service.parser.faculty_page.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtilsFactory {
  public DateUtils getDateUtils() {
    LocalDate date = ZonedDateTime.now(ZoneId.of("Europe/Minsk")).toLocalDate();
    if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
      return new DateUtils(date.plusDays(1));
    }
    return new DateUtils(date);
  }
}
