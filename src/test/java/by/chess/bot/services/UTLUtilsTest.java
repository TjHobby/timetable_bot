package by.chess.bot.services;

import by.chess.bot.service.parser.faculty_page.util.URLUtils;
import org.junit.jupiter.api.Test;

class UTLUtilsTest {
  @Test
  void UrlTest() {
    String id =
        URLUtils.extractSpreadsheetIdFromUrl(
            "https://docs.google.com/spreadsheets/d/1czi6uyWjVwk1uUAvLQL9EgOAs42W3J1jcSHZI26m4SQ/edit#gid=1448919428");
    assert id.equals("1czi6uyWjVwk1uUAvLQL9EgOAs42W3J1jcSHZI26m4SQ");
  }
}
