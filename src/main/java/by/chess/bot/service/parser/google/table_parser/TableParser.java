package by.chess.bot.service.parser.google.table_parser;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.timetable.entity.Timetable;
import by.chess.bot.model.timetable.entity.TimetableFactory;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multiset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
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

  public List<Timetable> parseTimetables() {
    List<Timetable> timetables = new LinkedList<>();
    for (int i = 0; i < getTableWidth(); i++) {
      String speciality = getSpeciality(i);
      if (StringUtils.isBlank(speciality)) {
        continue;
      }
      List<Timetable> timetablesByDays = parseSingleSpecialityTimetable(i);
      List<Timetable> resultTimetablesBySpeciality =
          setSpecialityToTimetables(timetablesByDays, speciality);
      timetables.addAll(resultTimetablesBySpeciality);
    }
    return timetables;
  }

  private List<Timetable> parseSingleSpecialityTimetable(int col) {
    List<Timetable> timetables = new LinkedList<>();
    ArrayListMultimap<String, Pair<String, String>> lessonsByDaysAndSpeciality =
        collectLessonsByDaysAndSpeciality(col);
    Multiset<String> daysOfWeek = lessonsByDaysAndSpeciality.keys();
    daysOfWeek.forEach(
        day -> {
          Timetable timetable =
              new TimetableFactory().getTimetable(day, lessonsByDaysAndSpeciality);
          timetables.add(timetable);
        });
    return timetables;
  }

  private ArrayListMultimap<String, Pair<String, String>> collectLessonsByDaysAndSpeciality(
      int specialityCol) {
    ArrayListMultimap<String, Pair<String, String>> result = ArrayListMultimap.create();
    for (List<String> row : tableContent) {
      String dayOfWeek = getDayOfWeek(row);
      if (dayOfWeek.isBlank()
          || DayOfWeek.getFullNames().stream().noneMatch(day -> StringUtils.containsIgnoreCase(dayOfWeek, day))) {
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

  private List<Timetable> setSpecialityToTimetables(List<Timetable> timetables, String speciality) {
    timetables.forEach(timetableEntity -> timetableEntity.setSpeciality(speciality));
    return timetables;
  }
}
