package fun.nibaba.wechat.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chenjiamin
 * @description 是否订阅公众号
 * @date 2021/5/18 2:27 下午
 */
@AllArgsConstructor
public enum Subscribe {

    /**
     * 是否订阅
     */
    Y(true),

    N(false);

    @Getter
    private final boolean booleanValue;

}
