package fun.nibaba.lazyfish.wechat.payment.exceptions;

/**
 * 微信支付-请求异常
 *
 * @author chenjiamin
 * @date 2021/5/24 5:11 下午
 */
public class WechatPaymentRequestException extends WechatPaymentException {

    public WechatPaymentRequestException() {
        super();
    }

    public WechatPaymentRequestException(String message) {
        super(message);
    }
}
