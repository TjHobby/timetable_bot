package by.chess.bot.service;

import by.chess.bot.model.timetable.TimetableRepository;
import by.chess.bot.model.timetable.entity.Timetable;
import by.chess.bot.service.parser.TimetableProvider;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Lazy(value = false)
@EnableAsync
public class UpdateTimetableService {
  private final TimetableRepository repository;
  private final TimetableProvider timetableProvider;

  @Scheduled(fixedRate = 15, timeUnit = TimeUnit.MINUTES)
  @Async
  public void updateTimetable() {
    List<Timetable> timetables = timetableProvider.getTimetables();
    timetables.forEach(repository::saveTimetable);
  }
}
