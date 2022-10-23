package by.chess.bot.telegram.handler;

import by.chess.bot.telegram.command.ReplyCommand;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ButtonHandler {

  final List<ReplyCommand> commands;

  public BotApiMethod<?> answerMessage(Message message) {
    if (message == null) {
      log.info("Empty callback");
      return new SendMessage();
    }
    long chatId = message.getChatId();
    String messageContent = message.getText();
    log.info("Received message from {}, content: {}", chatId, messageContent);
    return commands.stream()
        .filter(command -> command.isCommandSupported(chatId, messageContent))
        .findAny()
        .map(command -> command.handleMessage(chatId, messageContent))
        .orElse(
            (BotApiMethod)
                new SendMessage(String.valueOf(chatId), "Не удалось обработать запрос:с"));
  }
}
