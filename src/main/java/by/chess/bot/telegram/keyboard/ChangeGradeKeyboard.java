package by.chess.bot.telegram.keyboard;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeGradeKeyboard extends BaseKeyboard {

  public ReplyKeyboardMarkup getReplyKeyboard(List<String> grades) {
    grades.sort(String::compareTo);
    return prepareKeyboardWithRows(grades);
  }
}
