package by.chess.bot.service;

import by.chess.bot.misc.DayOfWeek;

public interface TimetableMessageProvider {
  String getTimetableMessage(long chatId, DayOfWeek day);
}
