package by.chess.bot.service.parser.faculty_page.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLUtils {
  private URLUtils() {}

  public static String extractSpreadsheetIdFromUrl(String url) {
    Pattern pattern = Pattern.compile("(?<=/d/)[^/]*");
    Matcher matcher = pattern.matcher(url);
    matcher.find();
    return matcher.group();
  }
}
