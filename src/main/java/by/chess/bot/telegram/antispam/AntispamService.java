package by.chess.bot.telegram.antispam;

import by.chess.bot.config.MessagesConfig;
import java.util.Map;
import java.util.WeakHashMap;
import org.springframework.stereotype.Component;

@Component
public class AntispamService {

  private final Map<Long, Long> users;
  private final MessagesConfig messagesConfig;

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
}
