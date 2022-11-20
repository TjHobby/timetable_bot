package by.chess.bot.telegram.keyboard;

import java.util.Arrays;
import java.util.Collections;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class SelectRoleKeyboard extends BaseKeyboard {
  public ReplyKeyboardMarkup getReplyKeyboard() {
    return prepareKeyboardWithTable(
        Arrays.asList(
            Collections.singletonList("Я студент"), Collections.singletonList("Я преподаватель")));
  }
}
