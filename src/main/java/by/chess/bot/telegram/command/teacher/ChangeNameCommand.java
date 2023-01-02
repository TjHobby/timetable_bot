package by.chess.bot.telegram.command.teacher;

import by.chess.bot.config.MessagesConfig;
import by.chess.bot.misc.Role;
import by.chess.bot.model.teacher.TeacherRepository;
import by.chess.bot.model.teacher.entity.Teacher;
import by.chess.bot.model.user.UserRepository;
import by.chess.bot.model.user.entity.User;
import by.chess.bot.telegram.command.ReplyCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

@Component
@AllArgsConstructor
public class ChangeNameCommand implements ReplyCommand {

  private final MessagesConfig messagesConfig;
  private final UserRepository userRepository;
  private final TeacherRepository teacherRepository;

  @Override
  public BotApiMethod<?> handleMessage(long chatId, String data) {
    setTeacherRole(chatId);
    initTeacher(chatId);
    SendMessage message = new SendMessage();
    message.setChatId(chatId);
    message.setReplyMarkup(new ReplyKeyboardRemove(true));
    message.setText(messagesConfig.getEnterTeacherNameMessage());
    return message;
  }

  private void initTeacher(long chatId) {
    Teacher teacher = new Teacher();
    teacher.setId(chatId);
    teacherRepository.save(teacher);
  }

  private void setTeacherRole(long chatId) {
    User user = new User(chatId, Role.TEACHER);
    userRepository.save(user);
  }

  @Override
  public boolean isCommandAvailable(long chatId, String text) {
    return text.equalsIgnoreCase("Я преподаватель");
  }
}
