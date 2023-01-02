package by.chess.bot.service.parser.google.api;

import by.chess.bot.config.GoogleSheetsApiConfig;
import by.chess.bot.service.parser.google.exception.GoogleSheetsException;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GoogleSheetApiService {

  private final GoogleSheetsApiConfig config;

  public List<List<String>> getSheetContent(String spreadsheetId) {
    Sheet sheet = getSheet(spreadsheetId);
    return new SheetContentParser(sheet).getSheetContent();
  }

  private Sheet getSheet(String spreadsheetId) {
    try {
      NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
      Sheets service =
          new Sheets.Builder(httpTransport, GsonFactory.getDefaultInstance(), authorize())
              .setApplicationName(config.getApplicationName())
              .build();
      Spreadsheet spreadsheet =
          service
              .spreadsheets()
              .get(spreadsheetId)
              .setRanges(Collections.singletonList(config.getLegacyRange()))
              .setIncludeGridData(true)
              .execute();
      return spreadsheet.getSheets().get(0);
    } catch (IOException | GeneralSecurityException e) {
      throw new GoogleSheetsException(
          String.format("Cannot get a sheets content, id %s", spreadsheetId), e);
    }
  }

  public Credential authorize() throws IOException {
    InputStream in = new FileInputStream(config.getCredentialsPath());
    GoogleClientSecrets clientSecrets =
        GoogleClientSecrets.load(GsonFactory.getDefaultInstance(), new InputStreamReader(in));
    GoogleAuthorizationCodeFlow flow;
    try {
      FileDataStoreFactory dataStore =
          new FileDataStoreFactory(new File(config.getAuthDataStore()));
      flow =
          new GoogleAuthorizationCodeFlow.Builder(
                  GoogleNetHttpTransport.newTrustedTransport(),
                  GsonFactory.getDefaultInstance(),
                  clientSecrets,
                  Collections.singletonList(SheetsScopes.SPREADSHEETS))
              .setDataStoreFactory(dataStore)
              .setAccessType("offline")
              .build();
    } catch (GeneralSecurityException e) {
      throw new GoogleSheetsException("Cannot authorize to Google API", e);
    }
    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
  }
}
