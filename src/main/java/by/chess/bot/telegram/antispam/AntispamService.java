package by.chess.bot.telegram.antispam;

import by.chess.bot.config.MessagesConfig;
import java.util.Map;
import java.util.WeakHashMap;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class AntispamService {

  Map<Long, Long> users;
  MessagesConfig messagesConfig;

  public AntispamService(MessagesConfig messagesConfig) {
    this.messagesConfig = messagesConfig;
    this.users = new WeakHashMap<>();
  }

  public boolean checkSpam(long chatId) {
    long currentTime = System.currentTimeMillis();
    if (users.get(chatId) == null
        || currentTime - users.get(chatId) > messagesConfig.getCooldown()) {
      users.put(chatId, currentTime);
      return false;
    }
    users.put(chatId, currentTime);
    return true;
  }

  public SendMessage getSpamReply(long chatId) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setText(messagesConfig.getCooldownMessage());
    sendMessage.setChatId(chatId);
    return sendMessage;
  }
}
