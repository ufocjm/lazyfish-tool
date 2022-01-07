package fun.nibaba.lazyfish.rabbit.delay;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 延迟队列初始化接口
 *
 * @author chenjiamin
 * @date 2021/12/30 5:52 PM
 */
@AllArgsConstructor
@Component
public class DelayRabbitInitRunnerImpl implements ApplicationRunner {

    private final List<ILazyRabbitDelay<?>> rabbitDelayList;

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void run(ApplicationArguments args) {
        rabbitDelayList.forEach(rabbitDelay -> {
            this.rabbitTemplate.execute(channel -> {
                rabbitDelay.declare(channel);
                return null;
            });
        });
    }

}
