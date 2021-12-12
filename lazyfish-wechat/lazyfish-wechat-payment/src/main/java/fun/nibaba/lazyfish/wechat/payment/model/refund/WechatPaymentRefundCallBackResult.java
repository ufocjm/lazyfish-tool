package fun.nibaba.lazyfish.wechat.payment.model.refund;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 微信支付-退款回调-返回结果
 *
 * @author chenjiamin
 * @date 2021/5/22 2:36 下午
 */
@Builder
@Getter
public class WechatPaymentRefundCallBackResult {

    /**
     * 返回状态码
     * SUCCESS/FAIL
     * <p>
     * 此字段是通信标识，非交易标识，退款是否成功需要解密后查看refund_status来判断
     */
    private final String returnCode;

    /**
     * 返回信息
     * 当return_code为FAIL时返回信息为错误原因 ，例如
     * 签名失败
     * 参数格式校验错误
     */
    private final String returnMsg;

    /**
     * 微信订单号
     */
    private final String transactionId;

    /**
     * 商户订单号
     */
    private final String outTradeNo;

    /**
     * 微信退款单号
     */
    private final String refundId;

    /**
     * 商户退款单号
     */
    private final String outRefundNo;

    /**
     * 订单金额
     */
    private final int totalFee;

    /**
     * 申请退款金额
     */
    private final int refundFee;

    /**
     * 退款金额
     */
    private final int settlementRefundFee;

    /**
     * 退款状态
     */
    private final String refundStatus;

    /**
     * 退款成功时间
     */
    private final LocalDateTime successTime;

    /**
     * 退款入账账户
     */
    private final String refundRecvAccount;

    /**
     * 退款资金来源
     */
    private final String refundAccount;

    /**
     * 退款发起来源
     */
    private final String refundRequestSource;
}
