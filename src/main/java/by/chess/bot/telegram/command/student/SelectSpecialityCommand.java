package by.chess.bot.telegram.command.student;

import by.chess.bot.config.MessagesConfig;
import by.chess.bot.model.student.StudentRepository;
import by.chess.bot.model.student.entity.Student;
import by.chess.bot.service.student.GetStudentTimetableInfoService;
import by.chess.bot.telegram.command.ReplyCommand;
import by.chess.bot.telegram.keyboard.GetTimetableKeyboard;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@AllArgsConstructor
public class SelectSpecialityCommand implements ReplyCommand {
  private static final GetTimetableKeyboard timetableKeyboard = new GetTimetableKeyboard();
  private final StudentRepository studentRepository;
  private final GetStudentTimetableInfoService timetableInfoService;
  private final MessagesConfig messagesConfig;

  @Override
  public BotApiMethod<?> handleMessage(long chatId, String data) {
    SendMessage reply = new SendMessage();
    reply.setChatId(chatId);
    updateUser(chatId, data);
    reply.setText(messagesConfig.getReadyMessage());
    reply.setReplyMarkup(new GetTimetableKeyboard().getReplyKeyboard());
    return reply;
  }

  @Override
  public boolean isCommandAvailable(long chatId, String text) {
    return timetableInfoService.getSpecialitiesByChatId(chatId).stream()
        .anyMatch(str -> str.equalsIgnoreCase(text));
  }

  private void updateUser(long chatId, String data) {
    Student entity = studentRepository.getStudentById(chatId);
    entity.setSpeciality(data);
    studentRepository.save(entity);
  }
}
