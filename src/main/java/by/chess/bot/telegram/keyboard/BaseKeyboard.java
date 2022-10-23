package by.chess.bot.telegram.keyboard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public abstract class BaseKeyboard {

  protected ReplyKeyboardMarkup prepareKeyboardWithRows(List<String> list) {
    List<List<String>> rows = new LinkedList<>();
    list.forEach(str -> rows.add(Collections.singletonList(str)));
    return prepareKeyboardWithTable(rows);
  }

  protected ReplyKeyboardMarkup prepareKeyboardWithTable(List<List<String>> table) {
    ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
    List<KeyboardRow> buttonRows = new LinkedList<>();
    table.forEach(
        row -> {
          KeyboardRow buttonRow = new KeyboardRow();
          row.forEach(buttonRow::add);
          buttonRows.add(buttonRow);
        });
    markup.setKeyboard(buttonRows);
    markup.setResizeKeyboard(true);
    return markup;
  }
}
