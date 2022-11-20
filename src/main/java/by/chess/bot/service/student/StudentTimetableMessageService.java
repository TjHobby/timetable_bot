package by.chess.bot.service.student;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.student.StudentRepository;
import by.chess.bot.model.student.entity.Student;
import by.chess.bot.model.timetable.TimetableRepository;
import by.chess.bot.model.timetable.entity.Timetable;
import by.chess.bot.service.TimetableMessageProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentTimetableMessageService implements TimetableMessageProvider {
  TimetableRepository timetableRepository;
  StudentRepository studentRepository;

  public String getTimetableMessage(long chatId, DayOfWeek day) {
    Student student = studentRepository.getStudentById(chatId);
    String key = Timetable.getTimetableKey(student.getGrade(), student.getSpeciality(), day);
    return timetableRepository.getTimetable(key).toString();
  }
}
