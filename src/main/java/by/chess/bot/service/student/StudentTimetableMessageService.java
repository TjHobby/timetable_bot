package by.chess.bot.service.student;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.student.StudentRepository;
import by.chess.bot.model.student.entity.Student;
import by.chess.bot.model.timetable.TimetableRepository;
import by.chess.bot.model.timetable.entity.Timetable;
import by.chess.bot.service.TimetableMessageProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentTimetableMessageService implements TimetableMessageProvider {
  private TimetableRepository timetableRepository;
  private StudentRepository studentRepository;

  public String getTimetableMessage(long chatId, DayOfWeek day) {
    Student student = studentRepository.getStudentById(chatId);
    String key = Timetable.getTimetableKey(student.getGrade(), student.getSpeciality(), day);
    return timetableRepository.getTimetable(key).toString();
  }
}
