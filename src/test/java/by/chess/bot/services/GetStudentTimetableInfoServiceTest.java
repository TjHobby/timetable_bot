package by.chess.bot.services;

import by.chess.bot.model.student.StudentRepository;
import by.chess.bot.model.timetable.TimetableRepository;
import by.chess.bot.providers.RepositoryMockProvider;
import by.chess.bot.service.student.GetStudentTimetableInfoService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetStudentTimetableInfoServiceTest {
  TimetableRepository timetableRepository;
  StudentRepository studentRepository;

  List<String> grades = Arrays.asList("1 курс", "2 курс", "3 курс", "4 курс");
  List<String> specialities = Arrays.asList("Экономическая информатика", "Экономическая теория");

  @Test
  void testGrades() {
    GetStudentTimetableInfoService service =
        new GetStudentTimetableInfoService(timetableRepository, studentRepository);
    Assertions.assertTrue(service.getGrades().containsAll(grades));
    Assertions.assertTrue(service.getSpecialitiesByChatId(0).containsAll(specialities));
    Assertions.assertTrue(service.getSpecialitiesByGrade("1 курс").containsAll(specialities));
  }

  @BeforeEach
  void init() {
    timetableRepository = RepositoryMockProvider.getTimetableRepoMock();
    studentRepository = RepositoryMockProvider.getStudentRepoMock();
  }
}
