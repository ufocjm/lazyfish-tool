package fun.nibaba.lazyfish.wechat.payment.enums;

/**
 * 微信支付-退款返回结果枚举
 *
 * @author chenjiamin
 * @date 2021/5/18 9:46 上午
 */
public enum RefundStatus {
    /**
     * SUCCESS-退款成功
     * PROCESSING—退款处理中
     * REFUNDCLOSE—退款关闭
     * CHANGE-退款异常
     */
    SUCCESS, PROCESSING, REFUNDCLOSE, CHANGE;
}
