package by.chess.bot.telegram.command.teacher;

import by.chess.bot.config.MessagesConfig;
import by.chess.bot.misc.Role;
import by.chess.bot.model.teacher.TeacherRepository;
import by.chess.bot.model.teacher.entity.Teacher;
import by.chess.bot.model.user.UserRepository;
import by.chess.bot.model.user.entity.User;
import by.chess.bot.telegram.command.ReplyCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeNameCommand implements ReplyCommand {

  final MessagesConfig messagesConfig;
  final UserRepository userRepository;
  final TeacherRepository teacherRepository;

  @Override
  public BotApiMethod<?> handleMessage(long chatId, String data) {
    setTeacherRole(chatId);
    Teacher teacher = new Teacher();
    teacher.setId(chatId);
    teacherRepository.save(teacher);
    SendMessage message = new SendMessage();
    message.setChatId(chatId);
    message.setText(messagesConfig.getEnterTeacherNameMessage());
    return message;
  }

  private void setTeacherRole(long chatId) {
    User user = userRepository.getUserById(chatId);
    user.setRole(Role.TEACHER);
    userRepository.save(user);
  }

  @Override
  public boolean isCommandSupported(long chatId, String text) {
    return text.equalsIgnoreCase("Я преподаватель");
  }


}
