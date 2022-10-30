package by.chess.bot.model.timetable;

import by.chess.bot.model.timetable.entity.Timetable;
import java.util.List;

public interface TimetableRepository {
  Timetable getTimetable(String key);

  List<Timetable> getAllTimetables();

  void saveTimetable(Timetable entity);

  void clearRepository();
}
