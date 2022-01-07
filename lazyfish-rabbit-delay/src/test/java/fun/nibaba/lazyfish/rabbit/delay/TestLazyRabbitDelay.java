package fun.nibaba.lazyfish.rabbit.delay;

import org.springframework.stereotype.Component;

/**
 * 测试
 *
 * @author chenjiamin
 * @date 2021/12/30 6:08 PM
 */
@Component
public class TestLazyRabbitDelay extends AbstractLazyRabbitDelay<TestLazyDelayMessageBody> {

    @Override
    public long getDelaySeconds() {
        return 5;
    }

    @Override
    public void listenerMessage(LazyDelayMessage<TestLazyDelayMessageBody> delayMessage) {
        System.out.println(Thread.currentThread() + " " + delayMessage);

    }

}
