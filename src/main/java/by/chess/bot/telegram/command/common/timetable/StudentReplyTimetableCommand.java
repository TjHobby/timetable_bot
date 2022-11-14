package by.chess.bot.telegram.command.common.timetable;

import by.chess.bot.model.user.UserRepository;
import by.chess.bot.model.user.entity.User;
import by.chess.bot.service.student.StudentTimetableMessageService;
import by.chess.bot.telegram.command.ReplyCommand;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentReplyTimetableCommand implements ReplyCommand {

  final UserRepository userRepository;
  final BaseReplyTimetableCommand baseReplyTimetableCommand;

  public StudentReplyTimetableCommand(
      StudentTimetableMessageService messageService, UserRepository userRepository) {
    this.baseReplyTimetableCommand = new BaseReplyTimetableCommand(messageService);
    this.userRepository = userRepository;
  }

  @Override
  public BotApiMethod<?> handleMessage(long chatId, String data) {
    return baseReplyTimetableCommand.handleMessage(chatId, data);
  }

  @Override
  public boolean isCommandSupported(long chatId, String text) {
    User user = userRepository.getUserById(chatId);
    if (user == null || !user.getRole().equals("student")) {
      return false;
    }
    return baseReplyTimetableCommand.isCommandSupported(text);
  }
}
