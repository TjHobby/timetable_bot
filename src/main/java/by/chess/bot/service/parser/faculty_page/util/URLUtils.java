package by.chess.bot.service.parser.faculty_page.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class URLUtils {

  public static String extractSpreadsheetIdFromUrl(String url) {
    Pattern pattern = Pattern.compile("(?<=/d/)[^/]*");
    Matcher matcher = pattern.matcher(url);
    if(!matcher.find()){
      throw new IllegalStateException("Cannot extract url from section");
    }
    return matcher.group();
  }
}
