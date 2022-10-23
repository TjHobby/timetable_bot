package by.chess.bot.service.parser.google.exception;

public class GoogleSheetsException extends RuntimeException {
  public GoogleSheetsException(String message, Exception e) {
    super(message, e);
  }
}
