package by.chess.bot.destroying;

import by.chess.bot.model.timetable.TimetableRepository;
import javax.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteTimetableOnShutdownTask {
  private final TimetableRepository timetableRepository;

  @PreDestroy
  public void onShutdown() {
    timetableRepository.clearRepository();
  }
}
