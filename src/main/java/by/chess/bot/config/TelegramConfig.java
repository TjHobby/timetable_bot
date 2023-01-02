package by.chess.bot.config;

import by.chess.bot.telegram.TimetableBot;
import by.chess.bot.telegram.handler.ButtonHandler;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@ConfigurationProperties(prefix = "telegram")
@Configuration
@Data
public class TelegramConfig {
  private String webhookHost;
  private String webhookPath;
  private String botName;
  private String botToken;

  @Bean
  public SetWebhook setWebhookInstance() {
    return SetWebhook.builder().url(webhookHost).build();
  }

  @Bean
  public TimetableBot springWebhookBot(SetWebhook setWebhook, ButtonHandler buttonHandler) {
    TimetableBot bot = new TimetableBot(setWebhook, buttonHandler);
    bot.setBotPath(webhookPath);
    bot.setBotUsername(botName);
    bot.setBotToken(botToken);
    return bot;
  }
}
