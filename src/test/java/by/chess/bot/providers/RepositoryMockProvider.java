package by.chess.bot.providers;

import static org.mockito.ArgumentMatchers.any;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.student.StudentRepository;
import by.chess.bot.model.student.entity.Student;
import by.chess.bot.model.timetable.TimetableRepository;
import by.chess.bot.model.timetable.entity.Timetable;
import by.chess.bot.model.user.UserRepository;
import org.mockito.Mockito;

public class RepositoryMockProvider {

  public static final int TEST_STUDENT_USER_ID = 0;
  public static final int TEST_TEACHER_USER_ID = 1;
  public static final int TEST_NULL_USER = 2;

  public static TimetableRepository getTimetableRepoMock() {
    TimetableRepository timetableRepository = Mockito.mock(TimetableRepository.class);
    Mockito.when(timetableRepository.getAllTimetables())
        .thenReturn(TimetableProvider.getTimetables());
    Student student = StudentProvider.getTestStudent();
    String timetableKey =
        Timetable.getTimetableKey(student.getGrade(), student.getSpeciality(), DayOfWeek.MONDAY);
    Mockito.when(timetableRepository.getTimetable(timetableKey))
        .thenReturn(TimetableProvider.getTimetables().get(0));
    return timetableRepository;
  }

  public static StudentRepository getStudentRepoMock() {
    StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
    Mockito.when(studentRepository.getStudentById(any(Long.class)))
        .thenReturn(StudentProvider.getTestStudent());
    return studentRepository;
  }

  public static UserRepository getUserRepository() {
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    Mockito.when(userRepository.getUserById(TEST_STUDENT_USER_ID))
        .thenReturn(UserProvider.getTestStudent());
    Mockito.when(userRepository.getUserById(TEST_TEACHER_USER_ID))
        .thenReturn(UserProvider.getTestTeacher());
    Mockito.when(userRepository.getUserById(TEST_NULL_USER)).thenReturn(null);
    return userRepository;
  }
}
