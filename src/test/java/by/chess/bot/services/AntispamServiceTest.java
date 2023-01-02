package by.chess.bot.services;

import by.chess.bot.config.MessagesConfig;
import by.chess.bot.telegram.antispam.AntispamService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AntispamServiceTest {

  MessagesConfig messagesConfig;

  @Test
  void testAntispam() {
    AntispamService antispamService = new AntispamService(messagesConfig);
    long id = 1;
    Assertions.assertFalse(antispamService.checkSpam(id));
    Assertions.assertTrue(antispamService.checkSpam(id));
    await();
    Assertions.assertFalse(antispamService.checkSpam(id));
  }

  void await() {
    CountDownLatch waiter = new CountDownLatch(1);
    try {
      waiter.await(1, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @BeforeEach
  void initializeConfig() {
    messagesConfig = Mockito.mock(MessagesConfig.class);
    Mockito.when(messagesConfig.getCooldown()).thenReturn(1L);
  }
}
