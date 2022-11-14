package by.chess.bot.telegram.keyboard;

import by.chess.bot.misc.DayOfWeek;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Component
public class GetTimetableKeyboard extends BaseKeyboard {
  public ReplyKeyboardMarkup getReplyKeyboard() {
    List<String> daysOfWeek =
        Arrays.stream(DayOfWeek.values()).map(DayOfWeek::getAlias).collect(Collectors.toList());
    return prepareKeyboardWithTable(
        Arrays.asList(daysOfWeek, Collections.singletonList("Изменить информацию")));
  }
}
