package fun.nibaba.lazyfish.rabbit.delay;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO
 *
 * @author chenjiamin
 * @date 2021/5/27 4:45 下午
 */
@SpringBootTest
public class JunitTest {

    @Autowired
    private TestLazyRabbitDelay testLazyRabbitDelay;

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            TestLazyDelayMessageBody testLazyDelayMessageBody = new TestLazyDelayMessageBody();
            testLazyDelayMessageBody.setTestMessage("测试");
            LazyDelayMessage<TestLazyDelayMessageBody> lazyDelayMessage = new LazyDelayMessage<>();
            lazyDelayMessage.setBody(testLazyDelayMessageBody);
            testLazyRabbitDelay.sendMessage(lazyDelayMessage);
        }
        Thread.sleep(10000L);

    }


}
