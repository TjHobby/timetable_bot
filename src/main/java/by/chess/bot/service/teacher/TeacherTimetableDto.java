package by.chess.bot.service.teacher;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@Data
public class TeacherTimetableDto {
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
  private final LocalTime time;
  private final String grade;
  private final String speciality;
  private final String lesson;

  public TeacherTimetableDto(String time, String grade, String speciality, String lesson) {
    this.time = parseTime(time);
    this.grade = grade;
    this.speciality = speciality;
    this.lesson = lesson;
  }

  private LocalTime parseTime(String timeString) {
    String processedTimeString = timeString.trim().replaceAll("\\W", ":");
    if (StringUtils.isBlank(processedTimeString)) {
      return LocalTime.of(0, 0);
    }
    return LocalTime.parse(processedTimeString, formatter);
  }

  public String getTimeString() {
    return time.format(formatter);
  }
}
