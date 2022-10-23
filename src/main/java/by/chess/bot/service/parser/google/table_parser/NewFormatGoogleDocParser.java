package by.chess.bot.service.parser.google.table_parser;

import java.util.List;

public class NewFormatGoogleDocParser extends TableParser {

  static final int DAY_OF_WEEK_COL = 0;

  static final int SPECIALITY_NAME_ROW = 1;
  static final int SPECIALITY_GROUP_NUM = 2;
  static final int TIME_COL = 1;

  public NewFormatGoogleDocParser(List<List<String>> tableContent) {
    super(tableContent);
  }

  @Override
  protected String getTime(List<String> row) {
    return getCellValue(row, TIME_COL);
  }

  @Override
  protected String getDayOfWeek(List<String> row) {
    return getCellValue(row, DAY_OF_WEEK_COL);
  }

  @Override
  protected String getSpeciality(int col) {
    String specialityName = getCellValue(tableContent.get(SPECIALITY_NAME_ROW), col);
    String specialityGroupNum = getCellValue(tableContent.get(SPECIALITY_GROUP_NUM), col);
    return specialityName + " " + specialityGroupNum;
  }

  @Override
  protected int getTableWidth() {
    return tableContent.get(0).size();
  }
}
