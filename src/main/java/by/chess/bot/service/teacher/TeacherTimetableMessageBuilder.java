package by.chess.bot.service.teacher;

import by.chess.bot.misc.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TeacherTimetableMessageBuilder {
  List<TeacherTimetableDto> timetables;

  public String buildMessage(DayOfWeek day) {
    StringBuilder result = new StringBuilder();
    result.append(day.getFullName());
    result.append("\n\n");
    LinkedHashMap<String, List<TeacherTimetableDto>> lessonsByTime = groupLessonsByTime();
    addLessons(result, lessonsByTime);
    return result.toString();
  }

  private void addLessons(StringBuilder result,
      LinkedHashMap<String, List<TeacherTimetableDto>> lessonsByTime) {
    for (Entry<String, List<TeacherTimetableDto>> lesson : lessonsByTime.entrySet()) {
      result.append(lesson.getKey());
      result.append("\n");
      addGrades(result, lesson);
    }
  }

  private void addGrades(StringBuilder result, Entry<String, List<TeacherTimetableDto>> lesson) {
    for (Entry<String, List<TeacherTimetableDto>> lessonByName :
        groupLessonsByName(lesson.getValue()).entrySet()) {
      result.append(lessonByName.getKey());
      result.append("\n");
      addSpecialities(result, lessonByName);
    }
  }

  private void addSpecialities(StringBuilder result,
      Entry<String, List<TeacherTimetableDto>> lessonByName) {
    for (Entry<String, List<TeacherTimetableDto>> lessonByGrade :
        groupLessonsByGrade(lessonByName.getValue()).entrySet()) {
      result.append(lessonByGrade.getKey());
      result.append("\n");
      result.append(
          lessonByGrade.getValue().stream()
              .map(TeacherTimetableDto::getSpeciality)
              .collect(Collectors.joining(", ")));
      result.append("\n");
    }
  }

  private LinkedHashMap<String, List<TeacherTimetableDto>> groupLessonsByTime() {
    return timetables.stream()
        .sorted(Comparator.comparing(TeacherTimetableDto::getTime))
        .collect(
            Collectors.groupingBy(
                TeacherTimetableDto::getTimeString, LinkedHashMap::new, Collectors.toList()));
  }

  private Map<String, List<TeacherTimetableDto>> groupLessonsByName(
      List<TeacherTimetableDto> timetables) {
    return timetables.stream().collect(Collectors.groupingBy(TeacherTimetableDto::getLesson));
  }

  private Map<String, List<TeacherTimetableDto>> groupLessonsByGrade(
      List<TeacherTimetableDto> timetables) {
    return timetables.stream().collect(Collectors.groupingBy(TeacherTimetableDto::getGrade));
  }
}
