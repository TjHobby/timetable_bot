package by.chess.bot.service;

import by.chess.bot.model.timetable_model.TimetableRepository;
import by.chess.bot.model.timetable_model.entity.TimetableEntity;
import by.chess.bot.service.parser.TimetableProvider;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Lazy(value = false)
@EnableAsync
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateTimetableService {
  TimetableRepository repository;
  TimetableProvider timetableProvider;

  @Scheduled(fixedRate = 15, timeUnit = TimeUnit.MINUTES)
  @Async
  public void updateTimetable() {
    List<TimetableEntity> timetables = timetableProvider.getTimetables();
    timetables.forEach(repository::saveTimetable);
  }
}
