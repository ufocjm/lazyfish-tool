package fun.nibaba.lazyfish.rabbit.delay;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 延迟消息
 *
 * @author chenjiamin
 * @date 2021/12/30 5:42 PM
 */
@Data
public class LazyDelayMessage<Body extends Serializable> implements Serializable {

    /**
     * 消息id
     */
    private String messageId;

    /**
     * 发送的时间
     */
    private LocalDateTime sendTime;

    /**
     * 消息体
     */
    private Body body;

}
