package by.chess.bot.telegram.command.common;

import by.chess.bot.config.MessagesConfig;
import by.chess.bot.model.user.UserRepository;
import by.chess.bot.telegram.command.student.BaseStudentReplyCommand;
import by.chess.bot.telegram.keyboard.SelectRoleKeyboard;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Arrays;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeInfoCommand extends BaseStudentReplyCommand {
  final MessagesConfig messagesConfig;
  final List<String> supportedCommands = Arrays.asList("/start", "Изменить информацию");

  public ChangeInfoCommand(MessagesConfig messagesConfig, UserRepository userRepository) {
    super(userRepository);
    this.messagesConfig = messagesConfig;
  }

  @Override
  public BotApiMethod<?> handleMessage(long chatId, String data) {
    SendMessage reply = new SendMessage();
    reply.setChatId(chatId);
    reply.setText(messagesConfig.getSelectRoleMessage());
    reply.setReplyMarkup(new SelectRoleKeyboard().getReplyKeyboard());
    return reply;
  }

  @Override
  public boolean isCommandSupported(long chatId, String text) {
    if (!super.isCommandSupported(chatId, text)) {
      return false;
    }
    return supportedCommands.stream().anyMatch(str -> str.equalsIgnoreCase(text));
  }
}
