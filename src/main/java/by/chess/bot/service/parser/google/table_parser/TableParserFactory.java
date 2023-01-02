package by.chess.bot.service.parser.google.table_parser;

import by.chess.bot.config.GradesConfig;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TableParserFactory {
  private final GradesConfig config;

  public TableParser createTableParser(String grade, List<List<String>> tableContent) {
    if (config.getGrades().contains(grade)) {
      return new NewFormatGoogleDocParser(tableContent);
    }
    return new LegacyGoogleDocParser(tableContent);
  }
}
