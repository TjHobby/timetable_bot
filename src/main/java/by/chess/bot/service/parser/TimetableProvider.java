package by.chess.bot.service.parser;

import by.chess.bot.model.timetable_model.entity.TimetableEntity;
import java.util.List;

public interface TimetableProvider {
  List<TimetableEntity> getTimetables();
}
