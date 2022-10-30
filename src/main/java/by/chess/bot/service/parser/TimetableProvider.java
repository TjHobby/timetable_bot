package by.chess.bot.service.parser;

import by.chess.bot.model.timetable.entity.Timetable;
import java.util.List;

public interface TimetableProvider {
  List<Timetable> getTimetables();
}
