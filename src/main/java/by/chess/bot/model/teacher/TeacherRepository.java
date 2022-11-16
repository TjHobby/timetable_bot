package by.chess.bot.model.teacher;

import by.chess.bot.model.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
  Teacher getTeacherById(long id);

  void deleteTeacherById(long id);
}
