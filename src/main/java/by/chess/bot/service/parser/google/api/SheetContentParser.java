package by.chess.bot.service.parser.google.api;

import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.GridRange;
import com.google.api.services.sheets.v4.model.RowData;
import com.google.api.services.sheets.v4.model.Sheet;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SheetContentParser {
  private final List<RowData> rows;
  private final MergedCells mergedCells;

  public SheetContentParser(Sheet sheet) {
    this.rows = sheet.getData().get(0).getRowData();
    this.mergedCells = new MergedCells(sheet);
  }

  public List<List<String>> getSheetContent() {
    return parseSheetTable();
  }

  private List<List<String>> parseSheetTable() {
    List<List<String>> result = new LinkedList<>();
    int tableWidth = rows.stream().map(AbstractMap::size).max(Integer::compareTo).orElse(0);
    for (int i = 0; i < rows.size(); i++) {
      result.add(getRowCellValues(rows.get(i), i, tableWidth));
    }
    return result.stream().filter(row -> !row.isEmpty()).collect(Collectors.toList());
  }

  private List<String> getRowCellValues(RowData row, int rowNum, int tableWidth) {
    List<String> result = new LinkedList<>();
    List<CellData> cells = row.getValues();
    if (cells == null || cells.size() < 2) {
      return result;
    }
    for (int j = 0; j < cells.size(); j++) {
      result.add(getCellValue(rowNum, j));
    }
    if (cells.size() != tableWidth) {
      return repairMergedRow(cells, rowNum);
    }
    return result;
  }

  private List<String> repairMergedRow(List<CellData> cells, int row) {
    List<String> result = new LinkedList<>();
    GridRange lastChecked = new GridRange();
    for (int i = 0; i < cells.size(); i++) {
      if (mergedCells.isCellMerged(row, i)
          && !mergedCells.getMergedRegion(row, i).equals(lastChecked)) {
        List<String> cellValue = getRepairedMergedCellValues(row, i);
        lastChecked = mergedCells.getMergedRegion(row, i);
        result.addAll(cellValue);
      }
      if (!mergedCells.isCellMerged(row, i)) {
        result.add(
            cells.get(i).getFormattedValue() == null ? "" : cells.get(i).getFormattedValue());
      }
    }
    return result;
  }

  private List<String> getRepairedMergedCellValues(int row, int col) {
    List<String> result = new LinkedList<>();
    GridRange mergedRange = mergedCells.getMergedRegion(row, col);
    for (int i = 0; i < mergedCells.getMergedRegionWidth(mergedRange); i++) {
      result.add(mergedCells.getMergedRegionValue(mergedRange));
    }
    return result;
  }

  private String getCellValue(int i, int j) {
    String cellValue = rows.get(i).getValues().get(j).getFormattedValue();
    if (cellValue == null) {
      return mergedCells.getMergedCellValue(i, j);
    }
    return cellValue;
  }
}
