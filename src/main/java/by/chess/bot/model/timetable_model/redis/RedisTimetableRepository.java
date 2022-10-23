package by.chess.bot.model.timetable_model.redis;

import by.chess.bot.model.timetable_model.TimetableRepository;
import by.chess.bot.model.timetable_model.entity.TimetableEntity;
import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisTimetableRepository implements TimetableRepository {

  private static final String REDIS_COLLECTION = "TIMETABLES";
  private final RedisTemplate<Object, Object> template;
  private final Gson gson;

  public RedisTimetableRepository(RedisTemplate<Object, Object> template) {
    this.template = template;
    this.gson = new Gson();
  }

  @Override
  public TimetableEntity getTimetable(String key) {
    String jsonEntity = String.valueOf(template.opsForHash().get(REDIS_COLLECTION, key));
    return gson.fromJson(jsonEntity, TimetableEntity.class);
  }

  @Override
  public List<TimetableEntity> getAllTimetables() {
    Map<Object, Object> timetables = template.opsForHash().entries(REDIS_COLLECTION);
    return timetables.values().stream()
        .map(o -> gson.fromJson((String) o, TimetableEntity.class))
        .collect(Collectors.toList());
  }

  @Override
  public void saveTimetable(TimetableEntity entity) {
    String jsonEntity = gson.toJson(entity);
    template.opsForHash().put(REDIS_COLLECTION, entity.getTimetableKey(), jsonEntity);
  }

  @Override
  public void clearRepository() {
    template.delete(REDIS_COLLECTION);
  }
}
