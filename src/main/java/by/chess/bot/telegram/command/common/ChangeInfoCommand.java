package by.chess.bot.telegram.command.common;

import by.chess.bot.config.MessagesConfig;
import by.chess.bot.model.student.StudentRepository;
import by.chess.bot.model.teacher.TeacherRepository;
import by.chess.bot.model.user.UserRepository;
import by.chess.bot.telegram.command.student.BaseStudentReplyCommand;
import by.chess.bot.telegram.keyboard.SelectRoleKeyboard;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class ChangeInfoCommand extends BaseStudentReplyCommand {
  private final MessagesConfig messagesConfig;
  private final List<String> supportedCommands = Arrays.asList("/start", "Изменить информацию");
  private final StudentRepository studentRepository;
  private final TeacherRepository teacherRepository;

  public ChangeInfoCommand(
      MessagesConfig messagesConfig,
      UserRepository userRepository,
      StudentRepository studentRepository,
      TeacherRepository teacherRepository) {
    super(userRepository);
    this.messagesConfig = messagesConfig;
    this.teacherRepository = teacherRepository;
    this.studentRepository = studentRepository;
  }

  @Override
  public BotApiMethod<?> handleMessage(long chatId, String data) {
    SendMessage reply = new SendMessage();
    studentRepository.deleteStudentById(chatId);
    teacherRepository.deleteTeacherById(chatId);
    reply.setChatId(chatId);
    reply.setText(messagesConfig.getSelectRoleMessage());
    reply.setReplyMarkup(new SelectRoleKeyboard().getReplyKeyboard());
    return reply;
  }

  @Override
  public boolean isCommandAvailable(long chatId, String text) {
    return supportedCommands.stream().anyMatch(str -> str.equalsIgnoreCase(text));
  }
}
