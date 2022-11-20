package by.chess.bot.telegram.command.student;

import by.chess.bot.misc.Role;
import by.chess.bot.model.user.UserRepository;
import by.chess.bot.model.user.entity.User;
import by.chess.bot.telegram.command.ReplyCommand;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseStudentReplyCommand implements ReplyCommand {

  UserRepository userRepository;

  @Override
  public boolean isCommandSupported(long chatId, String text) {
    User user = userRepository.getUserById(chatId);
    return user != null && user.getRole() == Role.STUDENT;
  }
}
