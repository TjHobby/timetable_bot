package by.chess.bot.providers;

import by.chess.bot.misc.Role;
import by.chess.bot.model.user.entity.User;

public class UserProvider {
  public static User getTestStudent() {
    User user = getUser();
    user.setRole(Role.STUDENT);
    return user;
  }

  public static User getTestTeacher() {
    User user = getUser();
    user.setRole(Role.TEACHER);
    return user;
  }

  private static User getUser() {
    User user = new User();
    user.setId(0);
    return user;
  }
}
