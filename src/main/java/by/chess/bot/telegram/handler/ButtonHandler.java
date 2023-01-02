package by.chess.bot.telegram.handler;

import by.chess.bot.config.MessagesConfig;
import by.chess.bot.telegram.antispam.AntispamService;
import by.chess.bot.telegram.command.ReplyCommand;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@AllArgsConstructor
@Slf4j
@SuppressWarnings("unchecked")
public class ButtonHandler {

  private final List<ReplyCommand> commands;
  private final AntispamService antispamService;
  private final MessagesConfig messagesConfig;

  public BotApiMethod<?> answerMessage(Message message) {
    if (message == null) {
      log.info("Empty callback");
      return new SendMessage();
    }
    long chatId = message.getChatId();
    String messageContent = message.getText();
    log.info("Received message from {}, content: {}", chatId, messageContent);
    if (antispamService.checkSpam(chatId)) {
      return getSpamReply(chatId);
    }
    return findAvailableCommand(chatId, messageContent);
  }

  private BotApiMethod<?> findAvailableCommand(long chatId, String messageContent) {
    return commands.stream()
        .filter(command -> command.isCommandAvailable(chatId, messageContent))
        .findAny()
        .map(command -> command.handleMessage(chatId, messageContent))
        .orElse(
            (BotApiMethod)
                new SendMessage(
                    String.valueOf(chatId), messagesConfig.getCommandNotFoundMessage()));
  }

  private SendMessage getSpamReply(long chatId) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setText(messagesConfig.getCooldownMessage());
    sendMessage.setChatId(chatId);
    return sendMessage;
  }
}
