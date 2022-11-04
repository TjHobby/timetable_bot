package by.chess.bot.telegram.command;

import by.chess.bot.misc.DayOfWeek;
import by.chess.bot.model.timetable.entity.Timetable;
import by.chess.bot.service.GetTimetableInfoService;
import by.chess.bot.telegram.keyboard.GetTimetableKeyboard;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReplyTimetableCommand implements ReplyCommand {
  final GetTimetableInfoService timetableInfoService;
  final List<String> supportedCommands = DayOfWeek.getAliases();

  @Override
  public BotApiMethod<?> handleMessage(long chatId, String data) {
    SendMessage reply = new SendMessage();
    reply.setChatId(chatId);
    Timetable timetable = timetableInfoService.getTimetable(chatId, DayOfWeek.parse(data));
    reply.setText(timetable.toString());
    reply.setReplyMarkup(new GetTimetableKeyboard().getReplyKeyboard());
    return reply;
  }

  @Override
  public boolean isCommandSupported(long chatId, String text) {
    return supportedCommands.stream().anyMatch(str -> str.equalsIgnoreCase(text));
  }
}
