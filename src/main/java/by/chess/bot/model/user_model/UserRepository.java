package by.chess.bot.model.user_model;

import by.chess.bot.model.user_model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  UserEntity getUserById(long id);
}
