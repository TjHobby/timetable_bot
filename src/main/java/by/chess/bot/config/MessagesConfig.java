package by.chess.bot.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "messages")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class MessagesConfig {
  String selectGradeMessage;
  String selectSpecialityMessage;
  String errorMessage;
  String selectRoleMessage;
  String enterTeacherNameMessage;
  String readyMessage;
}
