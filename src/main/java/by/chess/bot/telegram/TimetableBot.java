package by.chess.bot.telegram;

import by.chess.bot.telegram.handler.ButtonHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class TimetableBot extends SpringWebhookBot {
  private final ButtonHandler buttonHandler;
  String botPath;
  String botUsername;
  String botToken;

  public TimetableBot(SetWebhook setWebhook, ButtonHandler buttonHandler) {
    super(setWebhook);
    this.buttonHandler = buttonHandler;
  }

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    try {
      return handleUpdate(update);
    } catch (Exception e) {
      log.error("Error received on webhook update", e);
      return sendErrorMsg(update);
    }
  }

  private BotApiMethod<?> handleUpdate(Update update) {
    return buttonHandler.answerMessage(update.getMessage());
  }

  private BotApiMethod<?> sendErrorMsg(Update update) {
    long chatId =
        update.getMessage() == null
            ? update.getCallbackQuery().getMessage().getChatId()
            : update.getMessage().getChatId();
    return new SendMessage(String.valueOf(chatId), "Печалька, бот сломался:с");
  }
}
