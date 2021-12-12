package fun.nibaba.lazyfish.wechat.payment.exceptions;

/**
 * 微信支付-基础异常
 *
 * @author chenjiamin
 * @date 2021/5/24 5:11 下午
 */
public class WechatPaymentException extends RuntimeException {

    public WechatPaymentException() {
        super();
    }

    public WechatPaymentException(String message) {
        super(message);
    }
}
