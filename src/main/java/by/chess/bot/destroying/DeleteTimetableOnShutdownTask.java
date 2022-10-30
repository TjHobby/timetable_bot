package by.chess.bot.destroying;

import by.chess.bot.model.timetable.TimetableRepository;
import javax.annotation.PreDestroy;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteTimetableOnShutdownTask {
  TimetableRepository timetableRepository;

  @PreDestroy
  public void onShutdown() {
    timetableRepository.clearRepository();
  }
}
