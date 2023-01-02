package by.chess.bot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "messages")
@Data
public class MessagesConfig {
  private long cooldown;
  private String cooldownMessage;
  private String selectGradeMessage;
  private String selectSpecialityMessage;
  private String errorMessage;
  private String selectRoleMessage;
  private String enterTeacherNameMessage;
  private String readyMessage;
  private String commandNotFoundMessage;
}
