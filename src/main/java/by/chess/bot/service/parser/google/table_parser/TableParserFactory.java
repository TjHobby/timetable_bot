package by.chess.bot.service.parser.google.table_parser;

import by.chess.bot.config.GradesConfig;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableParserFactory {
  GradesConfig config;

  public TableParser createTableParser(String grade, List<List<String>> tableContent) {
    if (config.getGrades().contains(grade)) {
      return new NewFormatGoogleDocParser(tableContent);
    }
    return new LegacyGoogleDocParser(tableContent);
  }
}
