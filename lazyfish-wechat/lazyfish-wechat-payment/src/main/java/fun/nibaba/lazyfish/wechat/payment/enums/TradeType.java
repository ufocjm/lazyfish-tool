package fun.nibaba.lazyfish.wechat.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 微信支付-交易类型
 *
 * @author chenjiamin
 * @date 2021/5/14 10:05 下午
 */
@AllArgsConstructor
public enum TradeType {

    /**
     * JSAPI -JSAPI支付
     */
    JSAPI("JSAPI"),

    /**
     * NATIVE -Native支付
     */
    NATIVE("Native"),

    /**
     * APP -APP支付
     */
    APP("APP");

    @Getter
    private final String value;


}
