package by.chess.bot.services;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.timetable.entity.Timetable;
import by.chess.bot.providers.TimetableProvider;
import by.chess.bot.service.teacher.TeacherTimetableMessageBuilder;
import by.chess.bot.service.teacher.TeacherTimetableService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeacherTimetableMessageTest {

  private List<Timetable> timetables;
  private String expectedMessage;

  @Test
  void testTeacherMessage() {
    TeacherTimetableService timetableMapper =
        new TeacherTimetableService(timetables, "Иванов Е.И.", DayOfWeek.MONDAY);
    String message =
        new TeacherTimetableMessageBuilder(timetableMapper.getTeacherOccurrences())
            .buildMessage(DayOfWeek.MONDAY);
    TeacherTimetableService timetableMapper1 =
        new TeacherTimetableService(timetables, "ИвановЁ.И.", DayOfWeek.MONDAY);
    String message1 =
        new TeacherTimetableMessageBuilder(timetableMapper1.getTeacherOccurrences())
            .buildMessage(DayOfWeek.MONDAY);
    Assertions.assertEquals(message1, message);
    Assertions.assertEquals(expectedMessage, message);
  }

  @BeforeEach
  void initializeTimetables() {
    this.timetables = TimetableProvider.getTimetables();
  }

  @BeforeEach
  void initializeMessage() {
    expectedMessage =
        "Понедельник\n"
            + "\n"
            + "9:45\n"
            + "Какая-то пара (ауд. 86) Иванов Ё.И.\n"
            + "1 курс\n"
            + "Экономическая информатика, Экономическая информатика\n"
            + "\n"
            + "\n"
            + "12:45\n"
            + "Какая-то пара (ауд. 86) ИвановЕ.И.\n"
            + "2 курс\n"
            + "Экономическая теория\n"
            + "\n";
  }
}
