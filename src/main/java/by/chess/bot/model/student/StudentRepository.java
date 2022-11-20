package by.chess.bot.model.student;

import by.chess.bot.model.student.entity.Student;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
  Student getStudentById(long id);

  @Transactional
  void deleteStudentById(long id);
}
