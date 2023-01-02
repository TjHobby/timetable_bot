package by.chess.bot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "faculty")
@Data
public class FacultyPageConfig {
  private String url;
  private String sectionDatePattern;
}
