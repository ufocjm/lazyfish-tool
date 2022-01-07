package fun.nibaba.lazyfish.rabbit.delay;

import com.rabbitmq.client.*;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * 延迟消息队列抽象类
 *
 * @author chenjiamin
 * @date 2021/12/30 5:19 PM
 */
public abstract class AbstractLazyRabbitDelay<DelayMessage extends Serializable> implements ILazyRabbitDelay<LazyDelayMessage<DelayMessage>> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    protected Exchange exchange;

    protected Queue deadLetterQueue;

    protected Queue delayQueue;

    /**
     * 默认交换机
     *
     * @return 交换机
     */
    @Override
    public Exchange getExchange() {
        if (this.exchange == null) {
            this.exchange = ExchangeBuilder
                    .topicExchange(this.getClass().getSimpleName())
                    .build();
        }
        return this.exchange;
    }

    @Override
    public String getDeadLetterRoutingKey() {
        return this.getClass().getSimpleName() + "_DeadLetterRoutingKey";
    }

    @Override
    public Queue getDeadLetterQueue() {
        if (this.deadLetterQueue == null) {
            this.deadLetterQueue = QueueBuilder
                    .durable(this.getClass().getSimpleName() + "_DeadLetterQueue")
                    .withArgument(ILazyRabbitDelay.X_DEAD_LETTER_EXCHANGE_KEY, this.getExchange().getName())
                    .withArgument(ILazyRabbitDelay.X_DEAD_LETTER_ROUTING_KEY, this.getDelayRoutingKey())
                    .build();
        }
        return this.deadLetterQueue;
    }

    @Override
    public String getDelayRoutingKey() {
        return this.getClass().getSimpleName() + "_DelayRoutingKey";
    }

    @Override
    public Queue getDelayQueue() {
        if (this.delayQueue == null) {
            this.delayQueue = QueueBuilder
                    .durable(this.getClass().getSimpleName() + "_DelayQueue")
                    .build();
        }
        return this.delayQueue;
    }

    @Override
    public void sendMessage(LazyDelayMessage<DelayMessage> delayMessage) {
        CorrelationData correlationData = new CorrelationData(delayMessage.getMessageId());
        rabbitTemplate.convertAndSend(this.getExchange().getName(), this.getDeadLetterRoutingKey(), delayMessage, message -> {
            message.getMessageProperties().setExpiration(String.valueOf(this.getDelaySeconds() * 1000L));
            return message;
        }, correlationData);
    }

    @Override
    public Consumer getConsumer(Channel channel) {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                listenerMessage(deserialize(body));
            }
        };
    }

    @Override
    public void declare(Channel channel) throws IOException {
        Exchange exchange = this.getExchange();
        Queue deadLetterQueue = this.getDeadLetterQueue();
        Queue delayQueue = this.getDelayQueue();
        channel.exchangeDeclare(exchange.getName(), exchange.getType());
        channel.queueDeclare(deadLetterQueue.getName(), deadLetterQueue.isDurable(), deadLetterQueue.isExclusive(), deadLetterQueue.isAutoDelete(), deadLetterQueue.getArguments());
        channel.queueDeclare(delayQueue.getName(), delayQueue.isDurable(), delayQueue.isExclusive(), delayQueue.isAutoDelete(), delayQueue.getArguments());
        channel.queueBind(deadLetterQueue.getName(), exchange.getName(), this.getDeadLetterRoutingKey());
        channel.queueBind(delayQueue.getName(), exchange.getName(), this.getDelayRoutingKey());
        channel.basicConsume(delayQueue.getName(), true, getConsumer(channel));
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    protected LazyDelayMessage<DelayMessage> deserialize(byte[] body) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (LazyDelayMessage<DelayMessage>) objectInputStream.readObject();
    }

}
