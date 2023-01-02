package by.chess.bot.service.parser.faculty_page;

import by.chess.bot.config.FacultyPageConfig;
import by.chess.bot.config.GradesConfig;
import by.chess.bot.service.parser.faculty_page.misc.WeekPeriodServiceFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FacultyPageParser {
  private static final String SECTION_TAG_NAME = "section";
  private static final String LINK_TAG_NAME = "a";
  private final FacultyPageConfig facultyConfig;
  private final GradesConfig gradesConfig;

  public Map<String, String> getGoogleSheetUrl() {
    Document facultyPage = getFacultyPage();
    Elements sections = facultyPage.body().getElementsByTag(SECTION_TAG_NAME);
    Element sectionWithGoogleSheetsLinks = findSectionWithGoogleSheetsLinks(sections);
    Elements linksToGoogleSheets = sectionWithGoogleSheetsLinks.getElementsByTag(LINK_TAG_NAME);
    return parseGoogleSheetLinks(linksToGoogleSheets);
  }

  private Document getFacultyPage() {
    try {
      return Jsoup.connect(facultyConfig.getUrl()).get();
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  private Element findSectionWithGoogleSheetsLinks(Elements sections) {
    String sectionFilterText =
        new WeekPeriodServiceFactory()
            .getDateUtils()
            .getWeekPeriodString(facultyConfig.getSectionDatePattern());
    for (int i = 0; i < sections.size(); i++) {
      if (sections.get(i).text().contains(sectionFilterText)) {
        return sections.get(i + 1);
      }
    }
    throw new IllegalStateException("Cannot find Google Sheets url");
  }

  private Map<String, String> parseGoogleSheetLinks(Elements links) {
    Map<String, String> linksMap = new HashMap<>();
    List<String> allGrades =
        Stream.concat(gradesConfig.getGrades().stream(), gradesConfig.getLegacyGrades().stream())
            .collect(Collectors.toList());
    for (Element link : links) {
      if (allGrades.contains(link.text())) {
        linksMap.put(link.text(), link.attr("href"));
      }
    }
    return linksMap;
  }
}
