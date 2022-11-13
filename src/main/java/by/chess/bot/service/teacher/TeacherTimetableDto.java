package by.chess.bot.service.teacher;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherTimetableDto {
  LocalTime time;
  String grade;
  String speciality;
  String lesson;

  public TeacherTimetableDto(String time, String grade, String speciality, String lesson) {
    this.time = LocalTime.parse(time.trim());
    this.grade = grade;
    this.speciality = speciality;
    this.lesson = lesson;
  }

  public String getTimeString(){
    return time.format(DateTimeFormatter.ofPattern("HH:mm"));
  }
}
