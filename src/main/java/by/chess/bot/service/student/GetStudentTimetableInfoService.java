package by.chess.bot.service.student;

import by.chess.bot.model.student.StudentRepository;
import by.chess.bot.model.student.entity.Student;
import by.chess.bot.model.timetable.TimetableRepository;
import by.chess.bot.model.timetable.entity.Timetable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetStudentTimetableInfoService {
  private TimetableRepository timetableRepository;
  private StudentRepository studentRepository;

  public List<String> getGrades() {
    List<Timetable> timetables = timetableRepository.getAllTimetables();
    return timetables.stream().map(Timetable::getGrade).distinct().collect(Collectors.toList());
  }

  public List<String> getSpecialitiesByChatId(long chatId) {
    Student student = studentRepository.getStudentById(chatId);
    if (student == null) {
      return Collections.emptyList();
    }
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
