package by.chess.bot.misc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum DayOfWeek {
  MONDAY("Понедельник", "Пн"),
  TUESDAY("Вторник", "Вт"),
  WEDNESDAY("Среда", "Ср"),
  THURSDAY("Четверг", "Чт"),
  FRIDAY("Пятница", "Пт"),
  SATURDAY("Суббота", "Сб");

  private final String fullName;
  private final String alias;

  public static DayOfWeek parse(String val) {
    for (DayOfWeek day : DayOfWeek.values()) {
      if (StringUtils.containsIgnoreCase(val, day.fullName)
          || StringUtils.containsIgnoreCase(val, day.alias)) {
        return day;
      }
    }
    throw new IllegalStateException(String.format("Cannot parse DayOfWeek from %s", val));
  }
}
