package by.chess.bot.service.teacher;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.teacher.TeacherRepository;
import by.chess.bot.model.teacher.entity.Teacher;
import by.chess.bot.model.timetable.TimetableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetTeacherTimetableInfoService {
  TeacherRepository teacherRepository;
  TimetableRepository timetableRepository;

  public String getTimetableMessage(long chatId, DayOfWeek day) {
    Teacher teacher = teacherRepository.getTeacherById(chatId);
    TeacherTimetableMapper timetableMapper =
        new TeacherTimetableMapper(timetableRepository.getAllTimetables(), teacher.getName(), day);
    return new TeacherTimetableMessageBuilder(timetableMapper.getTeacherOccurences())
        .buildMessage(day);
  }
}
