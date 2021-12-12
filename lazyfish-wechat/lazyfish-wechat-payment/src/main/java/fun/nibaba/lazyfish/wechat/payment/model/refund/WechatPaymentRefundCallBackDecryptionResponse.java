package fun.nibaba.lazyfish.wechat.payment.model.refund;

import cn.hutool.core.util.StrUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentResponse;
import fun.nibaba.lazyfish.wechat.payment.utils.LocalDateTimeUtil;
import fun.nibaba.lazyfish.wechat.payment.utils.WechatAesUtil;

/**
 * 微信支付-退款回调-解密后实体
 *
 * @author chenjiamin
 * @date 2021/5/18 11:28 上午
 */
public class WechatPaymentRefundCallBackDecryptionResponse extends WechatPaymentResponse {

    private final XStream xStream = new XStream();

    public static final String WECHAT_PAYMENT_REFUND_CALLBACK_DECRYPTION_ALIAS = "root";

    /**
     * 解密后的xml字符串
     */
    private final String xmlStr;

    /**
     * 微信支付订单号
     */
    @XStreamAlias(value = "transaction_id")
    private String transactionId;

    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @XStreamAlias(value = "out_trade_no")
    private String outTradeNo;

    /**
     * 微信退款单号
     */
    @XStreamAlias(value = "refund_id")
    private String refundId;

    /**
     * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    @XStreamAlias(value = "out_refund_no")
    private String outRefundNo;

    /**
     * 订单总金额，单位为分，只能为整数
     */
    @XStreamAlias(value = "total_fee")
    private int totalFee;

    /**
     * 退款总金额,单位为分,可以做部分退款
     */
    @XStreamAlias(value = "refund_fee")
    private int refundFee;

    /**
     * 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    @XStreamAlias(value = "settlement_refund_fee")
    private int settlementRefundFee;

    /**
     * SUCCESS-退款成功
     * CHANGE-退款异常
     * REFUNDCLOSE—退款关闭
     */
    @XStreamAlias(value = "refund_status")
    private String refundStatus;

    /**
     * 资金退款至用户帐号的时间，格式2017-12-15 09:46:01
     */
    @XStreamAlias(value = "success_time")
    private String successTime;

    /**
     * 退款入账账户
     */
    @XStreamAlias(value = "refund_recv_accout")
    private String refundRecvAccount;

    /**
     * 退款资金来源
     * REFUND_SOURCE_RECHARGE_FUNDS 可用余额退款/基本账户
     * REFUND_SOURCE_UNSETTLED_FUNDS 未结算资金退款
     */
    @XStreamAlias(value = "refund_account")
    private String refundAccount;

    /**
     * 退款发起来源
     * API接口
     * VENDOR_PLATFORM商户平台
     */
    @XStreamAlias(value = "refund_request_source")
    private String refundRequestSource;


    /**
     * 只配置最简单的xml文本 后续经过 xStream 进行转换
     *
     * @param encryptionStr 微信返回
     * @param mchApiKey
     */
    public WechatPaymentRefundCallBackDecryptionResponse(String encryptionStr, String mchApiKey) {
        xStream.autodetectAnnotations(true);
        xStream.alias(WECHAT_PAYMENT_REFUND_CALLBACK_DECRYPTION_ALIAS, this.getClass());
        this.xmlStr = WechatAesUtil.decrypt(encryptionStr, mchApiKey);
    }

    /**
     * 构建
     *
     * @return
     */
    public WechatPaymentRefundCallBackDecryptionResponse build() {
        return (WechatPaymentRefundCallBackDecryptionResponse) xStream.fromXML(xmlStr);
    }


    /**
     * 实体转换
     *
     * @return
     */
    public WechatPaymentRefundCallBackResult toResult(WechatPaymentRefundCallBackEncryptionResponse encryptionResponse) {
        return WechatPaymentRefundCallBackResult.builder()
                .returnCode(encryptionResponse.getReturnCode())
                .returnMsg(encryptionResponse.getReturnMsg())
                .transactionId(this.transactionId)
                .outTradeNo(this.outTradeNo)
                .refundId(this.refundId)
                .outRefundNo(this.outRefundNo)
                .totalFee(this.totalFee)
                .refundFee(this.refundFee)
                .settlementRefundFee(this.settlementRefundFee)
                .refundStatus(this.refundStatus)
                .successTime(StrUtil.isNotBlank(this.successTime) ? LocalDateTimeUtil.parseLocalDateTime(this.successTime) : null)
                .refundRecvAccount(this.refundRecvAccount)
                .refundAccount(this.refundAccount)
                .refundRequestSource(this.refundRequestSource)
                .build();
    }


}
