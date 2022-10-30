package by.chess.bot.service;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.timetable.TimetableRepository;
import by.chess.bot.model.timetable.entity.Timetable;
import by.chess.bot.model.user.UserRepository;
import by.chess.bot.model.user.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
    return timetableRepository.findByGradeAndSpecialityAndDayOfWeek(
        user.getGrade(), user.getSpeciality(), day);
  }

  public List<String> getGrades() {
    Iterable<Timetable> timetables = timetableRepository.findAll();
    return StreamSupport.stream(timetables.spliterator(), false)
        .map(Timetable::getGrade)
        .distinct()
        .collect(Collectors.toList());
  }

  public List<String> getSpecialitiesByChatId(long chatId) {
    User user = userRepository.getUserById(chatId);
    return getSpecialitiesByGrade(user.getGrade());
  }

  public List<String> getSpecialitiesByGrade(String grade) {
    Iterable<Timetable> timetables = timetableRepository.findAll();
    return StreamSupport.stream(timetables.spliterator(), false)
        .filter(timetableEntity -> timetableEntity.getGrade().equals(grade))
        .map(Timetable::getSpeciality)
        .distinct()
        .collect(Collectors.toList());
  }
}
