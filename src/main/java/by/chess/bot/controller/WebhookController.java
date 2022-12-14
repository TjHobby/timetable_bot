package by.chess.bot.controller;

import by.chess.bot.telegram.TimetableBot;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@AllArgsConstructor
public class WebhookController {
  private final TimetableBot timetableBot;

  @PostMapping("/callback/bot")
  public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
    return timetableBot.onWebhookUpdateReceived(update);
  }
}
