package by.chess.bot.telegram.command.student;

import by.chess.bot.config.MessagesConfig;
import by.chess.bot.model.student.StudentRepository;
import by.chess.bot.model.student.entity.Student;
import by.chess.bot.service.student.GetStudentTimetableInfoService;
import by.chess.bot.telegram.command.ReplyCommand;
import by.chess.bot.telegram.keyboard.ChangeSpecialityKeyboard;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@AllArgsConstructor
public class SaveGradeCommand implements ReplyCommand {
  private final GetStudentTimetableInfoService timetableInfoService;
  private final StudentRepository studentRepository;
  private final MessagesConfig messagesConfig;

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
  public boolean isCommandAvailable(long chatId, String text) {
    return timetableInfoService.getGrades().stream().anyMatch(str -> str.equalsIgnoreCase(text));
  }

  private void updateUser(long chatId, String data) {
    Student entity = studentRepository.getStudentById(chatId);
    if (entity == null) {
      entity = new Student();
      entity.setId(chatId);
    }
    entity.setGrade(data);
    studentRepository.save(entity);
  }
}
