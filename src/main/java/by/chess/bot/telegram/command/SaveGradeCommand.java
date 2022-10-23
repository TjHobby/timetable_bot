package by.chess.bot.telegram.command;

import by.chess.bot.config.MessagesConfig;
import by.chess.bot.model.user_model.UserRepository;
import by.chess.bot.model.user_model.entity.UserEntity;
import by.chess.bot.service.GetTimetableInfoService;
import by.chess.bot.telegram.keyboard.ChangeSpecialityKeyboard;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveGradeCommand implements ReplyCommand {
  final GetTimetableInfoService timetableInfoService;
  final UserRepository userRepository;
  final MessagesConfig messagesConfig;

  @Override
  public BotApiMethod<?> handleMessage(long chatId, String data) {
    updateUser(chatId, data);
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.setText(messagesConfig.getSelectSpecialityMessage());
    sendMessage.setReplyMarkup(
        new ChangeSpecialityKeyboard()
            .getReplyKeyboard(timetableInfoService.getSpecialitiesByGrade(data)));
    return sendMessage;
  }

  @Override
  public boolean isCommandSupported(long chatId, String text) {
    return timetableInfoService.getGrades().stream().anyMatch(str -> str.equalsIgnoreCase(text));
  }

  private void updateUser(long chatId, String data) {
    UserEntity entity = userRepository.getUserById(chatId);
    if (entity == null) {
      entity = new UserEntity();
      entity.setId(chatId);
    }
    entity.setGrade(data);
    userRepository.save(entity);
  }
}
