package by.chess.bot.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "grades")
@Data
public class GradesConfig {
  private List<String> legacyGrades;
  private List<String> grades;
}
