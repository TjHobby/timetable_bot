package by.chess.bot.telegram.command.student;

import by.chess.bot.config.MessagesConfig;
import by.chess.bot.model.user.UserRepository;
import by.chess.bot.service.student.GetStudentTimetableInfoService;
import by.chess.bot.telegram.keyboard.ChangeGradeKeyboard;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Collections;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SelectGradeCommand extends BaseStudentReplyCommand {
  final GetStudentTimetableInfoService timetableInfoService;
  final MessagesConfig messagesConfig;
  final List<String> supportedCommands = Collections.singletonList("Я студент");

  public SelectGradeCommand(
      GetStudentTimetableInfoService timetableInfoService,
      MessagesConfig messagesConfig,
      UserRepository userRepository) {
    super(userRepository);
    this.timetableInfoService = timetableInfoService;
    this.messagesConfig = messagesConfig;
  }

  @Override
  public BotApiMethod<?> handleMessage(long chatId, String data) {
    SendMessage reply = new SendMessage();
    reply.setChatId(chatId);
    reply.setText(messagesConfig.getSelectGradeMessage());
    reply.setReplyMarkup(
        new ChangeGradeKeyboard().getReplyKeyboard(timetableInfoService.getGrades()));
    return reply;
  }

  @Override
  public boolean isCommandSupported(long chatId, String text) {
    if (!super.isCommandSupported(chatId, text)) {
      return false;
    }
    return supportedCommands.stream().anyMatch(str -> str.equalsIgnoreCase(text));
  }
}
