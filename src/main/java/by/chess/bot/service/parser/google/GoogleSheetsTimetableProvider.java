package by.chess.bot.service.parser.google;

import by.chess.bot.model.timetable_model.entity.TimetableEntity;
import by.chess.bot.service.parser.TimetableProvider;
import by.chess.bot.service.parser.faculty_page.FacultyPageParser;
import by.chess.bot.service.parser.faculty_page.util.URLUtils;
import by.chess.bot.service.parser.google.api.GoogleSheetApiService;
import by.chess.bot.service.parser.google.table_parser.TableParserFactory;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class GoogleSheetsTimetableProvider implements TimetableProvider {
  GoogleSheetApiService googleSheetApiService;
  FacultyPageParser facultyPageParser;
  TableParserFactory tableParserFactory;

  public List<TimetableEntity> getTimetables() {
    Map<String, String> gradesAndUrls = facultyPageParser.getGoogleSheetUrl();
    List<TimetableEntity> timetables = new LinkedList<>();
    gradesAndUrls.forEach(
        (grade, url) -> {
          List<List<String>> tableContent =
              googleSheetApiService.getSheetContent(URLUtils.extractSpreadsheetIdFromUrl(url));
          List<TimetableEntity> parsedTimetables =
              tableParserFactory.createTableParser(grade, tableContent).parseTimetables();
          List<TimetableEntity> result = setGradesToTimetables(parsedTimetables, grade);
          log.info("Parsed timetable for grade {}", grade);
          timetables.addAll(result);
        });
    return timetables;
  }

  private List<TimetableEntity> setGradesToTimetables(
      List<TimetableEntity> timetables, String grade) {
    timetables.forEach(timetable -> timetable.setGrade(grade));
    return timetables;
  }
}
