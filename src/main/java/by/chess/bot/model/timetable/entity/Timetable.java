package by.chess.bot.model.timetable.entity;

import by.chess.bot.misc.DayOfWeek;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@Builder
@Getter
@Setter
@RedisHash("Timetable")
public class Timetable implements Serializable {

  private static final long serialVersionUID = -2421290151039598746L;
  private String id;
  @Indexed private String grade;
  @Indexed private String speciality;
  @Indexed private DayOfWeek dayOfWeek;
  @Indexed private Map<String, String> lessons;

  public Timetable() {
    this.lessons = new LinkedHashMap<>();
  }

  public void addLessons(List<Pair<String, String>> lessons) {
    lessons.forEach(this::addLesson);
  }

  public void addLesson(Pair<String, String> lesson) {
    if (!lesson.getValue().isBlank()) {
      lessons.put(lesson.getKey(), lesson.getValue());
    }
  }

  public void assignId() {
    this.id = grade + "_" + speciality + "_" + dayOfWeek.getFullName();
  }

  @Override
  public String toString() {
    List<String> result = new LinkedList<>();
    result.add(dayOfWeek.getFullName());
    getLessons().forEach((time, name) -> result.add("\n" + time + "\n" + name));
    return String.join("\n", result);
  }
}
