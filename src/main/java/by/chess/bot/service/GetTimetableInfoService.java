package by.chess.bot.service;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.timetable.TimetableRepository;
import by.chess.bot.model.timetable.entity.Timetable;
import by.chess.bot.model.user.UserRepository;
import by.chess.bot.model.user.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetTimetableInfoService {
  TimetableRepository timetableRepository;
  UserRepository userRepository;

  public Timetable getTimetable(long chatId, DayOfWeek day) {
    User user = userRepository.getUserById(chatId);
    String key = Timetable.getTimetableKey(user.getGrade(), user.getSpeciality(), day);
    return timetableRepository.getTimetable(key);
  }

  public List<String> getGrades() {
    List<Timetable> timetables = timetableRepository.getAllTimetables();
    return timetables.stream()
        .map(Timetable::getGrade)
        .distinct()
        .collect(Collectors.toList());
  }

  public List<String> getSpecialitiesByChatId(long chatId) {
    User user = userRepository.getUserById(chatId);
    return getSpecialitiesByGrade(user.getGrade());
  }

  public List<String> getSpecialitiesByGrade(String grade) {
    List<Timetable> timetables = timetableRepository.getAllTimetables();
    return timetables.stream()
        .filter(timetableEntity -> timetableEntity.getGrade().equals(grade))
        .map(Timetable::getSpeciality)
        .distinct()
        .collect(Collectors.toList());
  }
}
