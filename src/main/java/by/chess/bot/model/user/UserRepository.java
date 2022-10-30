package by.chess.bot.model.user;

import by.chess.bot.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User getUserById(long id);
}
