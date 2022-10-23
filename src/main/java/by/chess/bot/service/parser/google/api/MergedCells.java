package by.chess.bot.service.parser.google.api;

import com.google.api.services.sheets.v4.model.GridRange;
import com.google.api.services.sheets.v4.model.Sheet;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MergedCells {
  final Sheet sheet;
  final List<GridRange> mergedCellRegions;

  public MergedCells(Sheet sheet) {
    this.sheet = sheet;
    this.mergedCellRegions = sheet.getMerges();
  }

  public String getMergedCellValue(int row, int col) {
    for (GridRange region : mergedCellRegions) {
      if (isCellInRegion(region, row, col)) {
        return getMergedRegionValue(region);
      }
    }
    return "";
  }

  public String getMergedRegionValue(GridRange range) {
    String value =
        sheet
            .getData()
            .get(0)
            .getRowData()
            .get(range.getStartRowIndex())
            .getValues()
            .get(range.getStartColumnIndex())
            .getFormattedValue();
    return value == null ? "" : value;
  }

  public int getMergedRegionWidth(GridRange range) {
    return range.getEndColumnIndex() - range.getStartColumnIndex();
  }

  public boolean isCellMerged(int row, int col) {
    for (GridRange range : mergedCellRegions) {
      if (isCellInRegion(range, row, col)) return true;
    }
    return false;
  }

  public GridRange getMergedRegion(int row, int col) {
    for (GridRange range : mergedCellRegions) {
      if (isCellInRegion(range, row, col)) return range;
    }
    throw new UnsupportedOperationException("Grid not found");
  }

  public boolean isCellInRegion(GridRange region, int row, int col) {
    return row >= region.getStartRowIndex()
        && row < region.getEndRowIndex()
        && col >= region.getStartColumnIndex()
        && col < region.getEndColumnIndex();
  }
}
