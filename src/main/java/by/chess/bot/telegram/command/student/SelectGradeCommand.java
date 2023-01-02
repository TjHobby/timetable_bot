package by.chess.bot.telegram.command.student;

import by.chess.bot.config.MessagesConfig;
import by.chess.bot.misc.Role;
import by.chess.bot.model.student.StudentRepository;
import by.chess.bot.model.student.entity.Student;
import by.chess.bot.model.user.UserRepository;
import by.chess.bot.model.user.entity.User;
import by.chess.bot.service.student.GetStudentTimetableInfoService;
import by.chess.bot.telegram.keyboard.ChangeGradeKeyboard;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class SelectGradeCommand extends BaseStudentReplyCommand {
  private final GetStudentTimetableInfoService timetableInfoService;
  private final MessagesConfig messagesConfig;

  private final StudentRepository studentRepository;
  private final List<String> supportedCommands = Collections.singletonList("Я студент");

  public SelectGradeCommand(
      GetStudentTimetableInfoService timetableInfoService,
      MessagesConfig messagesConfig,
      UserRepository userRepository,
      StudentRepository studentRepository) {
    super(userRepository);
    this.timetableInfoService = timetableInfoService;
    this.messagesConfig = messagesConfig;
    this.studentRepository = studentRepository;
  }

  @Override
  public BotApiMethod<?> handleMessage(long chatId, String data) {
    initStudent(chatId);
    SendMessage reply = new SendMessage();
    reply.setChatId(chatId);
    reply.setText(messagesConfig.getSelectGradeMessage());
    reply.setReplyMarkup(
        new ChangeGradeKeyboard().getReplyKeyboard(timetableInfoService.getGrades()));
    return reply;
  }

  private void initStudent(long chatId) {
    User user = new User(chatId, Role.STUDENT);
    userRepository.save(user);
    Student student = new Student();
    student.setId(chatId);
    studentRepository.save(student);
  }

  @Override
  public boolean isCommandAvailable(long chatId, String text) {
    return supportedCommands.stream().anyMatch(str -> str.equalsIgnoreCase(text));
  }
}
