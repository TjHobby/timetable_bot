package by.chess.bot.telegram.command.common.timetable;

import by.chess.bot.misc.Role;
import by.chess.bot.model.user.UserRepository;
import by.chess.bot.model.user.entity.User;
import by.chess.bot.telegram.command.ReplyCommand;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
public class TeacherReplyTimetableCommand implements ReplyCommand {

  private final UserRepository userRepository;
  private final BaseReplyTimetableCommand baseReplyTimetableCommand;

  public TeacherReplyTimetableCommand(
      @Qualifier("teacherReplyCommand") BaseReplyTimetableCommand command,
      UserRepository userRepository) {
    this.baseReplyTimetableCommand = command;
    this.userRepository = userRepository;
  }

  @Override
  public BotApiMethod<?> handleMessage(long chatId, String data) {
    return baseReplyTimetableCommand.handleMessage(chatId, data);
  }

  @Override
  public boolean isCommandAvailable(long chatId, String text) {
    User user = userRepository.getUserById(chatId);
    if (user == null || user.getRole() != Role.TEACHER) {
      return false;
    }
    return baseReplyTimetableCommand.isCommandSupported(text);
  }
}
