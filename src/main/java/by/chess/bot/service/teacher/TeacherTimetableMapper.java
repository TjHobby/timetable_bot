package by.chess.bot.service.teacher;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.timetable.entity.Timetable;
import java.util.LinkedList;
import java.util.List;
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
      Optional<Pair<String, String>> lesson = getLessonByTeacher(timetable);
      lesson.ifPresent(
          lessonPair ->
              result.add(
                  new TeacherTimetableDto(
                      lessonPair.getKey(),
                      timetable.getGrade(),
                      timetable.getSpeciality(),
                      lessonPair.getValue())));
    }
    return result;
  }

  private List<Timetable> filterTimetablesByDay() {
    return studentTimetables.stream()
        .filter(timetable -> timetable.getDayOfWeek() == day)
        .collect(Collectors.toList());
  }

  private Optional<Pair<String, String>> getLessonByTeacher(Timetable timetable) {
    return timetable.getLessons().entrySet().stream()
        .filter(
            entry ->
                StringUtils.containsIgnoreCase(
                    entry.getValue().replaceAll("\\W", ""), teacherName.replaceAll("\\W", "")))
        .map(entry -> Pair.of(entry.getKey(), entry.getValue()))
        .findAny();
  }
}
