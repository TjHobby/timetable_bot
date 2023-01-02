package by.chess.bot.service.parser.google.exception;

@SuppressWarnings("unused")
public class GoogleSheetsException extends RuntimeException {
  public GoogleSheetsException(String message, Throwable e) {
    super(message, e);
  }

  public GoogleSheetsException() {
    super();
  }

  public GoogleSheetsException(String cause) {
    super(cause);
  }

  public GoogleSheetsException(Throwable e) {
    super(e);
  }
}
