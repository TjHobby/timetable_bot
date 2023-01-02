package by.chess.bot.telegram.command;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface ReplyCommand {
  BotApiMethod<?> handleMessage(long chatId, String data);

  boolean isCommandAvailable(long chatId, String text);
}
