package by.chess.bot.model.timetable_model.entity;

import by.chess.bot.misc.DayOfWeek;
import com.google.common.collect.ArrayListMultimap;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class TimetableEntityFactory {
  public TimetableEntity getTimetable(
      String day, ArrayListMultimap<String, Pair<String, String>> lessonsByDays) {
    List<Pair<String, String>> lessons = lessonsByDays.get(day);
    TimetableEntity timetable = new TimetableEntity();
    timetable.setDayOfWeek(DayOfWeek.parse(day));
    timetable.addLessons(lessons);
    return timetable;
  }
}
