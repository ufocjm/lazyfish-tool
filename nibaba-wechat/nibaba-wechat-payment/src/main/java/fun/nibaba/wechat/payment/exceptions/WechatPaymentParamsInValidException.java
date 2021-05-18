package fun.nibaba.wechat.payment.exceptions;

/**
 * @author chenjiamin
 * @description 微信参数异常
 * @date 2021/5/18 9:15 上午
 */
public class WechatPaymentParamsInValidException extends RuntimeException {

    public WechatPaymentParamsInValidException(String message) {
        super(message);
    }

}
