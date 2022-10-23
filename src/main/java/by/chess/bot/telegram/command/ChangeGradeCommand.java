package by.chess.bot.telegram.command;

import by.chess.bot.config.MessagesConfig;
import by.chess.bot.service.GetTimetableInfoService;
import by.chess.bot.telegram.keyboard.ChangeGradeKeyboard;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@AllArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeGradeCommand implements ReplyCommand {
  final GetTimetableInfoService timetableInfoService;
  final MessagesConfig messagesConfig;
  final List<String> supportedCommands = Arrays.asList("/start", "Сменить курс");

  @Override
  public BotApiMethod<?> handleMessage(long chatId, String data) {
    SendMessage reply = new SendMessage();
    reply.setChatId(chatId);
    reply.setText(messagesConfig.getSelectGradeMessage());
    reply.setReplyMarkup(
        new ChangeGradeKeyboard().getReplyKeyboard(timetableInfoService.getGrades()));
    return reply;
  }

  @Override
  public boolean isCommandSupported(long chatId, String text) {
    return supportedCommands.stream().anyMatch(str -> str.equalsIgnoreCase(text));
  }
}
