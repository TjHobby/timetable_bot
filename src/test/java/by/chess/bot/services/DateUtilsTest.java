package by.chess.bot.services;

import by.chess.bot.service.parser.faculty_page.util.DateUtils;
import org.junit.jupiter.api.Test;

class DateUtilsTest {
  @Test
  void TestFromDate() {
    DateUtils utils = new DateUtils();
    System.out.println(utils.getFirstDayOfWeekString());
    System.out.println(utils.getLastDayOfWeekString());
  }
}
