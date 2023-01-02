package by.chess.bot.telegram.keyboard;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Component
@AllArgsConstructor
public class ChangeGradeKeyboard extends BaseKeyboard {

  public ReplyKeyboardMarkup getReplyKeyboard(List<String> grades) {
    grades.sort(String::compareTo);
    return prepareKeyboardWithRows(grades);
  }
}
