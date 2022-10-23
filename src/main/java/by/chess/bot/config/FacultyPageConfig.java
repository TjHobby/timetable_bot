package by.chess.bot.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "faculty")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class FacultyPageConfig {
  String url;
  String sectionDatePattern;
}
