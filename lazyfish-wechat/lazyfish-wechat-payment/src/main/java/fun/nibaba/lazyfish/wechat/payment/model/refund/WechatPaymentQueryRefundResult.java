package fun.nibaba.lazyfish.wechat.payment.model.refund;

import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentResult;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 微信支付-查询退款-返回结果
 *
 * @author chenjiamin
 * @date 2021/5/14 11:49 下午
 */
@ToString
@Getter
public class WechatPaymentQueryRefundResult extends WechatPaymentResult {

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
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    private final String outTradeNo;

    /**
     * 订单总金额，单位为分
     */
    private final int totalFee;

    /**
     * 现金支付金额订单现金支付金额，详见支付金额
     */
    private final int cashFee;

    /**
     * 当前返回退款笔数
     */
    private final int refundCount;

    /**
     * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    private final String outRefundNo;

    /**
     * 微信退款单号
     */
    private final String refundId;

    /**
     * 退款总金额,单位为分,可以做部分退款
     */
    private final int refundFee;

    /**
     * 退款状态：
     * SUCCESS—退款成功
     * REFUNDCLOSE—退款关闭。
     * PROCESSING—退款处理中
     * CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。$n为下标，从0开始编号。
     */
    private final String refundStatus;

    /**
     * 取当前退款单的退款入账方
     * 1）退回银行卡：
     * {银行名称}{卡类型}{卡尾号}
     * 2）退回支付用户零钱:
     * 支付用户零钱
     * 3）退还商户:
     * 商户基本账户
     * 商户结算银行账户
     * 4）退回支付用户零钱通:
     * 支付用户零钱通
     */
    private final String refundRecvAccount;

    /**
     * 退款成功时间，当退款状态为退款成功时有返回。$n为下标，从0开始编号。
     */
    private final LocalDateTime refundSuccessTime;

    @Builder
    public WechatPaymentQueryRefundResult(String returnCode,
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
                                          int totalFee,
                                          int cashFee,
                                          int refundCount,
                                          String outRefundNo,
                                          String refundId,
                                          int refundFee,
                                          String refundStatus,
                                          String refundRecvAccount,
                                          LocalDateTime refundSuccessTime) {
        super(returnCode, returnMsg, appid, mchId, nonceStr, sign);
        this.resultCode = resultCode;
        this.errCode = errCode;
        this.errCodeDes = errCodeDes;
        this.transactionId = transactionId;
        this.outTradeNo = outTradeNo;
        this.totalFee = totalFee;
        this.cashFee = cashFee;
        this.refundCount = refundCount;
        this.outRefundNo = outRefundNo;
        this.refundId = refundId;
        this.refundFee = refundFee;
        this.refundStatus = refundStatus;
        this.refundRecvAccount = refundRecvAccount;
        this.refundSuccessTime = refundSuccessTime;
    }
}
