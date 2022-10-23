package by.chess.bot.service;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.timetable_model.TimetableRepository;
import by.chess.bot.model.timetable_model.entity.TimetableEntity;
import by.chess.bot.model.user_model.UserRepository;
import by.chess.bot.model.user_model.entity.UserEntity;
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

  public TimetableEntity getTimetable(long chatId, DayOfWeek day) {
    UserEntity user = userRepository.getUserById(chatId);
    String key = TimetableEntity.getTimetableKey(user.getGrade(), user.getSpeciality(), day);
    return timetableRepository.getTimetable(key);
  }

  public List<String> getGrades() {
    List<TimetableEntity> timetables = timetableRepository.getAllTimetables();
    return timetables.stream()
        .map(TimetableEntity::getGrade)
        .distinct()
        .collect(Collectors.toList());
  }

  public List<String> getSpecialitiesByChatId(long chatId) {
    UserEntity user = userRepository.getUserById(chatId);
    return getSpecialitiesByGrade(user.getGrade());
  }

  public List<String> getSpecialitiesByGrade(String grade) {
    List<TimetableEntity> timetables = timetableRepository.getAllTimetables();
    return timetables.stream()
        .filter(timetableEntity -> timetableEntity.getGrade().equals(grade))
        .map(TimetableEntity::getSpeciality)
        .distinct()
        .collect(Collectors.toList());
  }
}
