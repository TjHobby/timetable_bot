package by.chess.bot.service.teacher;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.timetable.entity.Timetable;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

@AllArgsConstructor
public class TeacherTimetableMapper {
  private final List<Timetable> studentTimetables;
  private final String teacherName;
  private final DayOfWeek day;

  public List<TeacherTimetableDto> getTeacherOccurences() {
    List<TeacherTimetableDto> result = new LinkedList<>();
    for (Timetable timetable : filterTimetablesByDay()) {
      getLessonByTeacher(timetable)
          .forEach(
              (key, value) ->
                  result.add(
                      new TeacherTimetableDto(
                          key, timetable.getGrade(), timetable.getSpeciality(), value)));
    }
    return result;
  }

  private List<Timetable> filterTimetablesByDay() {
    return studentTimetables.stream()
        .filter(timetable -> timetable.getDayOfWeek() == day)
        .collect(Collectors.toList());
  }

  private Map<String, String> getLessonByTeacher(Timetable timetable) {
    return timetable.getLessons().entrySet().stream()
        .filter(
            entry ->
                StringUtils.containsIgnoreCase(
                    entry.getValue().replaceAll("\\w", "").replaceAll("[Ёё]", "е"), teacherName.replaceAll("\\w", "").replaceAll("[Ёё]", "е")))
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
  }
}
