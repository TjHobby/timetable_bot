package by.chess.bot.telegram.command.student;

import by.chess.bot.config.MessagesConfig;
import by.chess.bot.model.student.StudentRepository;
import by.chess.bot.model.student.entity.Student;
import by.chess.bot.service.student.GetStudentTimetableInfoService;
import by.chess.bot.telegram.command.ReplyCommand;
import by.chess.bot.telegram.keyboard.GetTimetableKeyboard;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SelectSpecialityCommand implements ReplyCommand {
  final StudentRepository studentRepository;
  final GetStudentTimetableInfoService timetableInfoService;
  final MessagesConfig messagesConfig;

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
  public boolean isCommandSupported(long chatId, String text) {
    return timetableInfoService.getSpecialitiesByChatId(chatId).stream()
        .anyMatch(str -> str.equalsIgnoreCase(text));
  }

  private void updateUser(long chatId, String data) {
    Student entity = studentRepository.getStudentById(chatId);
    entity.setSpeciality(data);
    studentRepository.save(entity);
  }
}
