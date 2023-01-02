package by.chess.bot.config;

import by.chess.bot.service.student.StudentTimetableMessageService;
import by.chess.bot.service.teacher.TeacherTimetableMessageService;
import by.chess.bot.telegram.command.common.timetable.BaseReplyTimetableCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReplyTimetableCommandConfig {

  @Bean(name = "studentReplyCommand")
  public BaseReplyTimetableCommand getStudentTimetableCommand(
      StudentTimetableMessageService service) {
    return new BaseReplyTimetableCommand(service);
  }

  @Bean(name = "teacherReplyCommand")
  public BaseReplyTimetableCommand getStudentTimetableCommand(
      TeacherTimetableMessageService service) {
    return new BaseReplyTimetableCommand(service);
  }
}
