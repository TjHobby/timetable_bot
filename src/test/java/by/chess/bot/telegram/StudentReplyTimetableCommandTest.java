package by.chess.bot.telegram;

import static org.mockito.ArgumentMatchers.any;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.user.UserRepository;
import by.chess.bot.providers.RepositoryMockProvider;
import by.chess.bot.service.student.StudentTimetableMessageService;
import by.chess.bot.telegram.command.common.timetable.BaseReplyTimetableCommand;
import by.chess.bot.telegram.command.common.timetable.StudentReplyTimetableCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

class StudentReplyTimetableCommandTest {

  UserRepository userRepository;
  BaseReplyTimetableCommand baseReplyTimetableCommand;

  @Test
  void testAvailability() {
    StudentReplyTimetableCommand studentReplyTimetableCommand =
        new StudentReplyTimetableCommand(baseReplyTimetableCommand, userRepository);
    int studentId = RepositoryMockProvider.TEST_STUDENT_USER_ID;
    int teacherId = RepositoryMockProvider.TEST_TEACHER_USER_ID;
    int nullUserId = RepositoryMockProvider.TEST_NULL_USER;
    Assertions.assertTrue(studentReplyTimetableCommand.isCommandAvailable(studentId, "Пн"));
    Assertions.assertTrue(studentReplyTimetableCommand.isCommandAvailable(studentId, "Вт"));
    Assertions.assertTrue(studentReplyTimetableCommand.isCommandAvailable(studentId, "Ср"));
    Assertions.assertTrue(studentReplyTimetableCommand.isCommandAvailable(studentId, "Чт"));
    Assertions.assertTrue(studentReplyTimetableCommand.isCommandAvailable(studentId, "Пт"));
    Assertions.assertTrue(studentReplyTimetableCommand.isCommandAvailable(studentId, "Сб"));
    Assertions.assertFalse(studentReplyTimetableCommand.isCommandAvailable(teacherId, "Сб"));
    Assertions.assertFalse(studentReplyTimetableCommand.isCommandAvailable(studentId, "Вс"));
    Assertions.assertFalse(
        studentReplyTimetableCommand.isCommandAvailable(studentId, "Изменить информацию"));
    Assertions.assertFalse(
        studentReplyTimetableCommand.isCommandAvailable(teacherId, "Изменить информацию"));
    Assertions.assertFalse(studentReplyTimetableCommand.isCommandAvailable(nullUserId, "Пн"));
  }

  @Test
  void testReply() {
    StudentReplyTimetableCommand studentReplyTimetableCommand =
        new StudentReplyTimetableCommand(baseReplyTimetableCommand, userRepository);
    Assertions.assertDoesNotThrow(
        () ->
            studentReplyTimetableCommand.handleMessage(
                RepositoryMockProvider.TEST_STUDENT_USER_ID, "Пн"));
    Assertions.assertEquals(
        "0", ((SendMessage) studentReplyTimetableCommand.handleMessage(0, "Пн")).getChatId());
  }

  @BeforeEach
  void init() {
    userRepository = RepositoryMockProvider.getUserRepository();
    StudentTimetableMessageService studentTimetableMessageService =
        Mockito.mock(StudentTimetableMessageService.class);
    Mockito.when(
            studentTimetableMessageService.getTimetableMessage(
                any(Long.class), any(DayOfWeek.class)))
        .thenReturn("");
    baseReplyTimetableCommand = new BaseReplyTimetableCommand(studentTimetableMessageService);
  }
}
