package by.chess.bot.services;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.student.StudentRepository;
import by.chess.bot.model.timetable.TimetableRepository;
import by.chess.bot.providers.RepositoryMockProvider;
import by.chess.bot.service.student.StudentTimetableMessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTimetableMessageServiceTest {

  StudentRepository studentRepository;
  TimetableRepository timetableRepository;
  String expectedMessage;

  @Test
  void testMessage() {
    StudentTimetableMessageService service =
        new StudentTimetableMessageService(timetableRepository, studentRepository);
    Assertions.assertEquals(expectedMessage, service.getTimetableMessage(0, DayOfWeek.MONDAY));
  }

  @BeforeEach
  void init() {
    studentRepository = RepositoryMockProvider.getStudentRepoMock();
    timetableRepository = RepositoryMockProvider.getTimetableRepoMock();
  }

  @BeforeEach
  void initMessage() {
    expectedMessage =
        "Понедельник\n"
            + "\n"
            + "9:45\n"
            + "Какая-то пара (ауд. 86) Иванов Ё.И.\n"
            + "\n"
            + "11:15\n"
            + "Какая-то пара (ауд. 86) Павлов И.И.";
  }
}
