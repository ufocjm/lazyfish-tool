package fun.nibaba.wechat.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chenjiamin
 * @description 交易类型
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
