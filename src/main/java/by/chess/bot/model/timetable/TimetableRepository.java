package by.chess.bot.model.timetable;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.timetable.entity.Timetable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepository extends CrudRepository<Timetable, String> {

  Timetable findByGradeAndSpecialityAndDayOfWeek(String grade, String speciality, DayOfWeek day);
}
