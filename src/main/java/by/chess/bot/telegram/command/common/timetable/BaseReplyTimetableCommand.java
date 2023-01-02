package by.chess.bot.telegram.command.common.timetable;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.service.TimetableMessageProvider;
import by.chess.bot.telegram.keyboard.GetTimetableKeyboard;
import java.util.List;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@AllArgsConstructor
public class BaseReplyTimetableCommand {
  private final TimetableMessageProvider timetableMessageProvider;
  private final List<String> supportedCommands = DayOfWeek.getAliases();

  public BotApiMethod<?> handleMessage(long chatId, String data) {
    SendMessage reply = new SendMessage();
    reply.setChatId(chatId);
    reply.setText(timetableMessageProvider.getTimetableMessage(chatId, DayOfWeek.parse(data)));
    reply.setReplyMarkup(new GetTimetableKeyboard().getReplyKeyboard());
    return reply;
  }

  public boolean isCommandSupported(String text) {
    return supportedCommands.stream().anyMatch(str -> str.equalsIgnoreCase(text));
  }
}
