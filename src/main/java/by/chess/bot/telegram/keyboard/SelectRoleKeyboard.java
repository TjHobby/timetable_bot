package by.chess.bot.telegram.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Arrays;
import java.util.Collections;

public class SelectRoleKeyboard extends BaseKeyboard {
  public ReplyKeyboardMarkup getReplyKeyboard() {
    return prepareKeyboardWithTable(
        Arrays.asList(
            Collections.singletonList("Я студент"), Collections.singletonList("Я преподаватель")));
  }
}
