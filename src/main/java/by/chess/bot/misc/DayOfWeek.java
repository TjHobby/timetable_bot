package by.chess.bot.misc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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

  public static List<String> getFullNames() {
    return Arrays.stream(DayOfWeek.values())
        .map(DayOfWeek::getFullName)
        .collect(Collectors.toList());
  }

  public static List<String> getAliases() {
    return Arrays.stream(DayOfWeek.values()).map(DayOfWeek::getAlias).collect(Collectors.toList());
  }
}
