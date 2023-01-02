package by.chess.bot.providers;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.timetable.entity.Timetable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class TimetableProvider {
  public static List<Timetable> getTimetables() {
    List<Timetable> timetables = new LinkedList<>();
    Timetable timetable1 =
        new Timetable(
            "1 курс", "Экономическая информатика", DayOfWeek.MONDAY, new LinkedHashMap<>());
    timetable1.addLesson(Pair.of("9:45", "Какая-то пара (ауд. 86) Иванов Ё.И."));
    timetable1.addLesson(Pair.of("11:15", "Какая-то пара (ауд. 86) Павлов И.И."));
    Timetable timetable2 =
        new Timetable(
            "1 курс", "Экономическая информатика", DayOfWeek.TUESDAY, new LinkedHashMap<>());
    timetable2.addLesson(Pair.of("9:45", "Какая-то пара (ауд. 86) Иванов Е.И."));
    timetable2.addLesson(Pair.of("11:15", "Какая-то пара (ауд. 86) Павлов И.И."));
    Timetable timetable3 =
        new Timetable("1 курс", "Экономическая теория", DayOfWeek.WEDNESDAY, new LinkedHashMap<>());
    timetable3.addLesson(Pair.of("9:45", "Какая-то пара (ауд. 86) Иванов Е.И."));
    timetable3.addLesson(Pair.of("11:15", "Какая-то пара (ауд. 86) Павлов И.И."));
    Timetable timetable4 =
        new Timetable("2 курс", "Экономическая теория", DayOfWeek.MONDAY, new LinkedHashMap<>());
    timetable4.addLesson(Pair.of("12:45", "Какая-то пара (ауд. 86) ИвановЕ.И."));
    timetable4.addLesson(Pair.of("11:15", "Какая-то пара (ауд. 86) Павлов И.И."));
    Timetable timetable5 =
        new Timetable("4 курс", "Экономика", DayOfWeek.FRIDAY, new LinkedHashMap<>());
    timetable5.addLesson(Pair.of("12:45", "Какая-то пара (ауд. 86) СидоровЕ.И."));
    timetable5.addLesson(Pair.of("11:15", "Какая-то пара1 (ауд. 86) Павлов И.И."));
    timetables.add(timetable1);
    Timetable timetable6 =
        new Timetable("3 курс", "Финансы и кредит", DayOfWeek.FRIDAY, new LinkedHashMap<>());
    timetable6.addLesson(Pair.of("12:45", "Какая-то пара2 (ауд. 86) СидоровЕ.И."));
    timetable6.addLesson(Pair.of("11:15", "Какая-то пара1 (ауд. 86) Павлов И.И."));
    timetables.add(timetable1);
    timetables.add(timetable2);
    timetables.add(timetable3);
    timetables.add(timetable4);
    timetables.add(timetable5);
    timetables.add(timetable6);
    return timetables;
  }
}
