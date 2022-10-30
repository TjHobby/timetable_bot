package by.chess.bot.model.timetable.redis;

import by.chess.bot.model.timetable.TimetableRepository;
import by.chess.bot.model.timetable.entity.Timetable;
import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.redis.core.RedisCallback;
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
  public Timetable getTimetable(String key) {
    String jsonEntity = String.valueOf(template.opsForHash().get(REDIS_COLLECTION, key));
    return gson.fromJson(jsonEntity, Timetable.class);
  }

  @Override
  public List<Timetable> getAllTimetables() {
    Map<Object, Object> timetables = template.opsForHash().entries(REDIS_COLLECTION);
    return timetables.values().stream()
        .map(o -> gson.fromJson((String) o, Timetable.class))
        .collect(Collectors.toList());
  }

  @Override
  public void saveTimetable(Timetable entity) {
    String jsonEntity = gson.toJson(entity);
    rewriteTimetable(entity.getTimetableKey(), jsonEntity);
  }

  @Override
  public void clearRepository() {
    template.delete(REDIS_COLLECTION);
  }

  private void rewriteTimetable(String key, String jsonValue) {
    template.execute(
        (RedisCallback<String>)
            connection -> {
              connection.multi();
              template.opsForHash().delete(REDIS_COLLECTION, key);
              template.opsForHash().put(REDIS_COLLECTION, key, jsonValue);
              connection.exec();
              return null;
            });
  }
}
