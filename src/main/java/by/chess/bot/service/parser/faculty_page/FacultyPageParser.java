package by.chess.bot.service.parser.faculty_page;

import by.chess.bot.config.FacultyPageConfig;
import by.chess.bot.config.GradesConfig;
import by.chess.bot.service.parser.faculty_page.util.DateUtils;
import by.chess.bot.service.parser.faculty_page.util.DateUtilsFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FacultyPageParser {
  static final String SECTION_TAG_NAME = "section";
  static final String LINK_TAG_NAME = "a";
  final FacultyPageConfig facultyConfig;
  final GradesConfig gradesConfig;

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
    String sectionFilterText = makeSectionFilterText();
    for (int i = 0; i < sections.size(); i++) {
      if (sections.get(i).text().contains(sectionFilterText)) {
        return sections.get(i + 1);
      }
    }
    throw new IllegalStateException("Cannot find Google Sheets url");
  }

  private String makeSectionFilterText() {
    DateUtils dateUtils = new DateUtilsFactory().getDateUtils();
    return String.format(
        facultyConfig.getSectionDatePattern(),
        dateUtils.getFirstDayOfWeekString(),
        dateUtils.getLastDayOfWeekString());
  }

  private Map<String, String> parseGoogleSheetLinks(Elements links) {
    Map<String, String> linksMap = new HashMap<>();
    List<String> allGrades =
        Stream.concat(gradesConfig.getGrades().stream(), gradesConfig.getLegacyGrades().stream())
            .collect(Collectors.toList());
    links.forEach(
        link -> {
          if (allGrades.contains(link.text())) {
            linksMap.put(link.text(), link.attr("href"));
          }
        });
    return linksMap;
  }
}
