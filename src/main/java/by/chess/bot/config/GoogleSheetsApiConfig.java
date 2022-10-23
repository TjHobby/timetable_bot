package by.chess.bot.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sheets")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class GoogleSheetsApiConfig {
  String applicationName;
  String credentialsPath;
  String authDataStore;
  String legacyRange;
  String range;
}
