package by.chess.bot.telegram.command.teacher;

import by.chess.bot.config.MessagesConfig;
import by.chess.bot.misc.Role;
import by.chess.bot.model.teacher.TeacherRepository;
import by.chess.bot.model.teacher.entity.Teacher;
import by.chess.bot.model.user.UserRepository;
import by.chess.bot.model.user.entity.User;
import by.chess.bot.telegram.command.ReplyCommand;
import by.chess.bot.telegram.keyboard.GetTimetableKeyboard;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@AllArgsConstructor
public class SaveNameCommand implements ReplyCommand {

  private final TeacherRepository teacherRepository;
  private final UserRepository userRepository;
  private final MessagesConfig messagesConfig;

  @Override
  public BotApiMethod<?> handleMessage(long chatId, String data) {
    saveTeacherName(chatId, data);
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.setText(messagesConfig.getReadyMessage());
    sendMessage.setReplyMarkup(new GetTimetableKeyboard().getReplyKeyboard());
    return sendMessage;
  }

  private void saveTeacherName(long chatId, String data) {
    Teacher teacher = teacherRepository.getTeacherById(chatId);
    teacher.setName(data);
    teacherRepository.save(teacher);
  }

  @Override
  public boolean isCommandAvailable(long chatId, String text) {
    User user = userRepository.getUserById(chatId);
    Teacher teacher = teacherRepository.getTeacherById(chatId);
    return user != null
        && user.getRole() == Role.TEACHER
        && teacher != null
        && StringUtils.isBlank(teacher.getName());
  }
}
