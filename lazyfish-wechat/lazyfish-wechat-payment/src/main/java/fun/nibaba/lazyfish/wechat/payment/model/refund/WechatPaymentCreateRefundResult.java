package fun.nibaba.lazyfish.wechat.payment.model.refund;

import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentResult;
import lombok.Builder;
import lombok.Getter;

/**
 * 微信支付-创建退款-返回结果
 * @author chenjiamin
 * @date 2021/5/14 11:49 下午
 */
@Getter
public class WechatPaymentCreateRefundResult extends WechatPaymentResult {

    /**
     * 业务结果
     * SUCCESS/FAIL
     */
    private final String resultCode;

    /**
     * 错误代码
     * 当result_code为FAIL时返回错误代码，详细参见下文错误列表
     */
    private final String errCode;

    /**
     * 错误代码描述
     * 当result_code为FAIL时返回错误描述，详细参见下文错误列表
     */
    private final String errCodeDes;

    /**
     * 微信支付订单号
     */
    private final String transactionId;

    /**
     * 商户订单号
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    private final String outTradeNo;

    /**
     * 微信退款单号
     */
    private final String refundId;

    /**
     * 退款金额
     * 退款总金额,单位为分,可以做部分退款
     */
    private final Integer refundFee;

    /**
     * 标价金额
     * 订单总金额，单位为分
     */
    private final Integer totalFee;

    /**
     * 现金支付金额
     * 现金支付金额，单位为分，只能为整数
     */
    private final Integer cashFee;

    @Builder
    public WechatPaymentCreateRefundResult(String returnCode,
                                           String returnMsg,
                                           String appid,
                                           String mchId,
                                           String nonceStr,
                                           String sign,
                                           String resultCode,
                                           String errCode,
                                           String errCodeDes,
                                           String transactionId,
                                           String outTradeNo,
                                           String refundId,
                                           Integer refundFee,
                                           Integer totalFee,
                                           Integer cashFee) {
        super(returnCode, returnMsg, appid, mchId, nonceStr, sign);
        this.resultCode = resultCode;
        this.errCode = errCode;
        this.errCodeDes = errCodeDes;
        this.transactionId = transactionId;
        this.outTradeNo = outTradeNo;
        this.refundId = refundId;
        this.refundFee = refundFee;
        this.totalFee = totalFee;
        this.cashFee = cashFee;
    }
}
