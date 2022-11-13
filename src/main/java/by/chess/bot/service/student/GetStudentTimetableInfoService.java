package by.chess.bot.service.student;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.student.StudentRepository;
import by.chess.bot.model.student.entity.Student;
import by.chess.bot.model.timetable.TimetableRepository;
import by.chess.bot.model.timetable.entity.Timetable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetStudentTimetableInfoService {
  TimetableRepository timetableRepository;
  StudentRepository studentRepository;

  public Timetable getTimetable(long chatId, DayOfWeek day) {
    Student student = studentRepository.getStudentById(chatId);
    String key = Timetable.getTimetableKey(student.getGrade(), student.getSpeciality(), day);
    return timetableRepository.getTimetable(key);
  }

  public List<String> getGrades() {
    List<Timetable> timetables = timetableRepository.getAllTimetables();
    return timetables.stream().map(Timetable::getGrade).distinct().collect(Collectors.toList());
  }

  public List<String> getSpecialitiesByChatId(long chatId) {
    Student student = studentRepository.getStudentById(chatId);
    return getSpecialitiesByGrade(student.getGrade());
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
