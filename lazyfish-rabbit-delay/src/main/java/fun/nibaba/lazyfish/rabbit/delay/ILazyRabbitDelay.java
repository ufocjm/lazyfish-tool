package fun.nibaba.lazyfish.rabbit.delay;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;

import java.io.IOException;
import java.io.Serializable;

/**
 * 延迟消息队列接口
 *
 * @author chenjiamin
 * @date 2021/12/24 6:05 下午
 */
public interface ILazyRabbitDelay<DelayMessage extends Serializable> {

    String X_DEAD_LETTER_EXCHANGE_KEY = "x-dead-letter-exchange";

    String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    /**
     * 获取交换机
     *
     * @return 交换机
     */
    Exchange getExchange();

    /**
     * 获取死信routingKey
     *
     * @return 死信routingKey
     */
    String getDeadLetterRoutingKey();

    /**
     * 获取死信队列
     *
     * @return 死信队列
     */
    Queue getDeadLetterQueue();

    /**
     * 获取延迟routingKey
     *
     * @return 延迟routingKey
     */
    String getDelayRoutingKey();

    /**
     * 获取延迟队列
     *
     * @return 延迟队列
     */
    Queue getDelayQueue();


    /**
     * 发送消息
     *
     * @param delayMessage 消息
     */
    void sendMessage(DelayMessage delayMessage);

    /**
     * 获取消费者
     *
     * @param channel amqp channel
     * @return 消费者
     */
    Consumer getConsumer(Channel channel);

    /**
     * 定义整个消息队列
     *
     * @param channel amqp channel
     * @throws IOException IOException
     */
    void declare(Channel channel) throws IOException;

    /**
     * 获取延时的时间
     *
     * @return 延时的时间
     */
    long getDelaySeconds();

    /**
     * 监听消息
     *
     * @param message 消息
     */
    void listenerMessage(DelayMessage message);


}
