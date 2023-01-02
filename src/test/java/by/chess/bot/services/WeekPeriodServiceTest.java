package by.chess.bot.services;

import by.chess.bot.service.parser.faculty_page.misc.WeekPeriodService;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WeekPeriodServiceTest {
  @Test
  void testPeriod() {
    WeekPeriodService service = new WeekPeriodService(LocalDate.of(2023, 1, 2));
    String period = service.getWeekPeriodString("%s - %s");
    Assertions.assertEquals("2 января - 7 января", period);
  }
}
