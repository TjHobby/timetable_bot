package by.chess.bot.services;

import by.chess.bot.misc.DayOfWeek;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DayParsingTest {
  @Test
  void parseCorrect() {
    Assertions.assertEquals(DayOfWeek.MONDAY, DayOfWeek.parse("Пн"));
    Assertions.assertEquals(DayOfWeek.MONDAY, DayOfWeek.parse("пн"));
    Assertions.assertEquals(DayOfWeek.MONDAY, DayOfWeek.parse("Понедельник"));
    Assertions.assertEquals(DayOfWeek.MONDAY, DayOfWeek.parse("ПОНЕДЕЛЬНИК"));
    Assertions.assertEquals(DayOfWeek.TUESDAY, DayOfWeek.parse("Вт"));
    Assertions.assertEquals(DayOfWeek.TUESDAY, DayOfWeek.parse("вт"));
    Assertions.assertEquals(DayOfWeek.TUESDAY, DayOfWeek.parse("Вторник"));
    Assertions.assertEquals(DayOfWeek.TUESDAY, DayOfWeek.parse("ВТОРНИК"));
    Assertions.assertEquals(DayOfWeek.WEDNESDAY, DayOfWeek.parse("Ср"));
    Assertions.assertEquals(DayOfWeek.WEDNESDAY, DayOfWeek.parse("ср"));
    Assertions.assertEquals(DayOfWeek.WEDNESDAY, DayOfWeek.parse("Среда"));
    Assertions.assertEquals(DayOfWeek.WEDNESDAY, DayOfWeek.parse("СРЕДА"));
    Assertions.assertEquals(DayOfWeek.THURSDAY, DayOfWeek.parse("Чт"));
    Assertions.assertEquals(DayOfWeek.THURSDAY, DayOfWeek.parse("чт"));
    Assertions.assertEquals(DayOfWeek.THURSDAY, DayOfWeek.parse("Четверг"));
    Assertions.assertEquals(DayOfWeek.THURSDAY, DayOfWeek.parse("ЧЕТВЕРГ"));
    Assertions.assertEquals(DayOfWeek.FRIDAY, DayOfWeek.parse("Пт"));
    Assertions.assertEquals(DayOfWeek.FRIDAY, DayOfWeek.parse("пт"));
    Assertions.assertEquals(DayOfWeek.FRIDAY, DayOfWeek.parse("Пятница"));
    Assertions.assertEquals(DayOfWeek.FRIDAY, DayOfWeek.parse("ПЯТНИЦА"));
    Assertions.assertEquals(DayOfWeek.SATURDAY, DayOfWeek.parse("Сб"));
    Assertions.assertEquals(DayOfWeek.SATURDAY, DayOfWeek.parse("сб"));
    Assertions.assertEquals(DayOfWeek.SATURDAY, DayOfWeek.parse("Суббота"));
    Assertions.assertEquals(DayOfWeek.SATURDAY, DayOfWeek.parse("СУББОТА"));
  }

  @Test
  void parseInvalid() {
    Assertions.assertThrows(IllegalStateException.class, () -> DayOfWeek.parse("xxx"));
  }
}
