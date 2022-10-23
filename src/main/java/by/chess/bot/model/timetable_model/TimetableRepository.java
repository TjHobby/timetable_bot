package by.chess.bot.model.timetable_model;

import by.chess.bot.model.timetable_model.entity.TimetableEntity;
import java.util.List;

public interface TimetableRepository {
  TimetableEntity getTimetable(String key);

  List<TimetableEntity> getAllTimetables();

  void saveTimetable(TimetableEntity entity);

  void clearRepository();
}
