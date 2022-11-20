package by.chess.bot.model.teacher;

import by.chess.bot.model.teacher.entity.Teacher;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
  Teacher getTeacherById(long id);

  @Transactional
  void deleteTeacherById(long id);
}
