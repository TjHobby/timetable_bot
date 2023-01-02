package by.chess.bot.service.teacher;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.teacher.TeacherRepository;
import by.chess.bot.model.teacher.entity.Teacher;
import by.chess.bot.model.timetable.TimetableRepository;
import by.chess.bot.service.TimetableMessageProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherTimetableMessageService implements TimetableMessageProvider {
  private TeacherRepository teacherRepository;
  private TimetableRepository timetableRepository;

  public String getTimetableMessage(long chatId, DayOfWeek day) {
    Teacher teacher = teacherRepository.getTeacherById(chatId);
    TeacherTimetableService timetableMapper =
        new TeacherTimetableService(timetableRepository.getAllTimetables(), teacher.getName(), day);
    return new TeacherTimetableMessageBuilder(timetableMapper.getTeacherOccurrences())
        .buildMessage(day);
  }
}
