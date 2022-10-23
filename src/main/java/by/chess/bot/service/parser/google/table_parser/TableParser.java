package by.chess.bot.service.parser.google.table_parser;

import by.chess.bot.model.timetable_model.entity.TimetableEntity;
import by.chess.bot.model.timetable_model.entity.TimetableEntityFactory;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multiset;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

@AllArgsConstructor
public abstract class TableParser {
  protected List<List<String>> tableContent;

  protected abstract String getTime(List<String> row);

  protected abstract String getDayOfWeek(List<String> row);

  protected abstract String getSpeciality(int col);

  protected abstract int getTableWidth();

  public List<TimetableEntity> parseTimetables() {
    List<TimetableEntity> timetables = new LinkedList<>();
    for (int i = 0; i < getTableWidth(); i++) {
      String speciality = getSpeciality(i);
      if (StringUtils.isBlank(speciality)) {
        continue;
      }
      List<TimetableEntity> timetablesByDays = parseSingleSpecialityTimetable(i);
      List<TimetableEntity> resultTimetablesBySpeciality =
          setSpecialityToTimetables(timetablesByDays, speciality);
      timetables.addAll(resultTimetablesBySpeciality);
    }
    return timetables;
  }

  private List<TimetableEntity> parseSingleSpecialityTimetable(int col) {
    List<TimetableEntity> timetables = new LinkedList<>();
    ArrayListMultimap<String, Pair<String, String>> lessonsByDaysAndSpeciality =
        collectLessonsByDaysAndSpeciality(col);
    Multiset<String> daysOfWeek = lessonsByDaysAndSpeciality.keys();
    daysOfWeek.forEach(
        day -> {
          TimetableEntity timetable =
              new TimetableEntityFactory().getTimetable(day, lessonsByDaysAndSpeciality);
          timetables.add(timetable);
        });
    return timetables;
  }

  private ArrayListMultimap<String, Pair<String, String>> collectLessonsByDaysAndSpeciality(
      int specialityCol) {
    ArrayListMultimap<String, Pair<String, String>> result = ArrayListMultimap.create();
    for (List<String> row : tableContent) {
      final String dayOfWeek = getDayOfWeek(row);
      if (dayOfWeek.isBlank()) {
        continue;
      }
      result.put(dayOfWeek, Pair.of(getTime(row), getLesson(row, specialityCol)));
    }
    return result;
  }

  protected String getLesson(List<String> row, int col) {
    return getCellValue(row, col);
  }

  protected String getCellValue(List<String> row, int index) {
    return row.get(index).trim();
  }

  private List<TimetableEntity> setSpecialityToTimetables(
      List<TimetableEntity> timetables, String speciality) {
    timetables.forEach(timetableEntity -> timetableEntity.setSpeciality(speciality));
    return timetables;
  }
}
