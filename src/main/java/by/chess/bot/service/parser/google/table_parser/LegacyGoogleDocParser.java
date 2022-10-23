package by.chess.bot.service.parser.google.table_parser;

import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class LegacyGoogleDocParser extends TableParser {

  static final int SPECIALITIES_ROW = 2;
  static final int DAY_OF_WEEK_COL = 0;
  static final int TIME_COL = 1;

  public LegacyGoogleDocParser(List<List<String>> tableData) {
    super(tableData);
  }

  @Override
  protected String getTime(List<String> row) {
    return row.get(TIME_COL);
  }

  @Override
  protected String getDayOfWeek(List<String> row) {
    return row.get(DAY_OF_WEEK_COL);
  }

  @Override
  protected String getSpeciality(int col) {
    return tableContent.get(SPECIALITIES_ROW).get(col);
  }

  @Override
  protected int getTableWidth() {
    return tableContent.get(0).size();
  }
}
