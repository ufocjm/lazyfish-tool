package fun.nibaba.lazyfish.wechat.payment.model.refund;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import fun.nibaba.lazyfish.wechat.payment.enums.ResultCode;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentResponse;
import lombok.Data;

/**
 * 微信支付-创建退款-请求返回结果
 *
 * @author chenjiamin
 * @date 2021/5/14 11:49 下午
 */
@Data
public class WechatPaymentCreateRefundResponse extends WechatPaymentResponse {

    /**
     * 业务结果
     * SUCCESS/FAIL
     */
    @XStreamAlias(value = "result_code")
    private String resultCode;

    /**
     * 错误代码
     * 当result_code为FAIL时返回错误代码，详细参见下文错误列表
     */
    @XStreamAlias(value = "err_code")
    private String errCode;

    /**
     * 错误代码描述
     * 当result_code为FAIL时返回错误描述，详细参见下文错误列表
     */
    @XStreamAlias(value = "err_code_des")
    private String errCodeDes;

    /**
     * 微信支付订单号
     */
    @XStreamAlias(value = "transaction_id")
    private String transactionId;

    /**
     * 商户订单号
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
     * 退款金额
     * 退款总金额,单位为分,可以做部分退款
     */
    @XStreamAlias(value = "refund_fee")
    private Integer refundFee;

    /**
     * 标价金额
     * 订单总金额，单位为分
     */
    @XStreamAlias(value = "total_fee")
    private Integer totalFee;

    /**
     * 现金支付金额
     * 现金支付金额，单位为分，只能为整数
     */
    @XStreamAlias(value = "cash_fee")
    private Integer cashFee;


    /**
     * 转换实体
     *
     * @return
     */
    public WechatPaymentCreateRefundResult toResult() {
        return WechatPaymentCreateRefundResult.builder()
                .returnCode(this.returnCode)
                .returnMsg(this.returnMsg)
                .appid(this.appid)
                .mchId(this.mchId)
                .nonceStr(this.nonceStr)
                .sign(this.sign)
                .resultCode(this.resultCode)
                .errCode(this.errCode)
                .errCodeDes(this.errCodeDes)
                .transactionId(this.transactionId)
                .outTradeNo(this.outTradeNo)
                .refundId(this.refundId)
                .refundFee(this.refundFee)
                .totalFee(this.totalFee)
                .cashFee(this.cashFee)
                .build();
    }

    /**
     * 执行成功
     *
     * @return
     */
    public boolean handleSuccess() {
        return ResultCode.SUCCESS.toString().equals(this.resultCode);
    }

}
