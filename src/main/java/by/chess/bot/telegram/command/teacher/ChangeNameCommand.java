package by.chess.bot.telegram.command.teacher;

import by.chess.bot.config.MessagesConfig;
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

  @Override
  public BotApiMethod<?> handleMessage(long chatId, String data) {

    SendMessage message = new SendMessage();
    message.setChatId(chatId);
    message.setText(messagesConfig.getEnterTeacherNameMessage());
    return message;
  }

  @Override
  public boolean isCommandSupported(long chatId, String text) {
    return text.equalsIgnoreCase("Я преподаватель");
  }


}
