package by.chess.bot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sheets")
@Data
public class GoogleSheetsApiConfig {
  private String applicationName;
  private String credentialsPath;
  private String authDataStore;
  private String legacyRange;
  private String range;
}
