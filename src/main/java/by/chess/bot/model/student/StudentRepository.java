package by.chess.bot.model.student;

import by.chess.bot.model.student.entity.Student;
import by.chess.bot.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
  Student getStudentById(long id);
}
