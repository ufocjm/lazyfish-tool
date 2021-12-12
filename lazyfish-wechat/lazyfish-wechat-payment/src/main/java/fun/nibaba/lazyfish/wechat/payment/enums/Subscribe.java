package fun.nibaba.lazyfish.wechat.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 微信支付-是否订阅公众号
 *
 * @author chenjiamin
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
