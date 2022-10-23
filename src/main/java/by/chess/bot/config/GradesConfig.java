package by.chess.bot.config;

import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "grades")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class GradesConfig {
  List<String> legacyGrades;
  List<String> grades;
}
