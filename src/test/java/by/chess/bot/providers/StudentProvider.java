package by.chess.bot.providers;

import by.chess.bot.model.student.entity.Student;

public class StudentProvider {
  public static Student getTestStudent() {
    Student student = new Student();
    student.setId(0);
    student.setSpeciality("Экономическая информатика");
    student.setGrade("1 курс");
    return student;
  }
}
