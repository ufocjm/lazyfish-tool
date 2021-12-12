package fun.nibaba.lazyfish.wechat.payment.exceptions;

/**
 * 微信支付-参数异常
 *
 * @author chenjiamin
 * @date 2021/5/18 9:15 上午
 */
public class WechatPaymentParamsInValidException extends WechatPaymentException {

    public WechatPaymentParamsInValidException(String message) {
        super(message);
    }

}
