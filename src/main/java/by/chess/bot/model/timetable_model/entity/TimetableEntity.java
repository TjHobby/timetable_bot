package by.chess.bot.model.timetable_model.entity;

import by.chess.bot.misc.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

@AllArgsConstructor
@Data
@Builder
public class TimetableEntity {
  private String grade;
  private String speciality;
  private DayOfWeek dayOfWeek;
  private LinkedHashMap<String, String> lessons;

  public TimetableEntity() {
    this.lessons = new LinkedHashMap<>();
  }

  public static String getTimetableKey(String grade, String speciality, DayOfWeek day) {
    return grade + "_" + speciality + "_" + day.getFullName();
  }

  public String getTimetableKey() {
    return getTimetableKey(grade, speciality, dayOfWeek);
  }

  public void addLessons(List<Pair<String, String>> lessons) {
    lessons.forEach(this::addLesson);
  }

  public void addLesson(Pair<String, String> lesson) {
    if (!lesson.getValue().isBlank()) {
      lessons.put(lesson.getKey(), lesson.getValue());
    }
  }

  @Override
  public String toString() {
    List<String> result = new LinkedList<>();
    result.add(dayOfWeek.getFullName());
    getLessons().forEach((time, name) -> result.add("\n" + time + "\n" + name));
    return String.join("\n", result);
  }
}
