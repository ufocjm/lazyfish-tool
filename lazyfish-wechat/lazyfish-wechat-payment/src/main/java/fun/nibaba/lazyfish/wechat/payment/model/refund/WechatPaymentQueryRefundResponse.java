package fun.nibaba.lazyfish.wechat.payment.model.refund;

import cn.hutool.core.util.StrUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import fun.nibaba.lazyfish.wechat.payment.enums.ResultCode;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentResponse;
import fun.nibaba.lazyfish.wechat.payment.utils.LocalDateTimeUtil;
import lombok.Data;

/**
 * 微信支付-查询退款-请求返回结果
 *
 * @author chenjiamin
 * @date 2021/5/14 11:49 下午
 */
@Data
public class WechatPaymentQueryRefundResponse extends WechatPaymentResponse {

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
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @XStreamAlias(value = "out_trade_no")
    private String outTradeNo;

    /**
     * 订单总金额，单位为分
     */
    @XStreamAlias(value = "total_fee")
    private int totalFee;

    /**
     * 现金支付金额订单现金支付金额，详见支付金额
     */
    @XStreamAlias(value = "cash_fee")
    private int cashFee;

    /**
     * 当前返回退款笔数
     */
    @XStreamAlias(value = "refund_count")
    private Integer refundCount;

    /**
     * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    @XStreamAlias(value = "out_refund_no_1")
    private String outRefundNo;

    /**
     * 微信退款单号
     */
    @XStreamAlias(value = "refund_id_0")
    private String refundId;

    /**
     * 退款总金额,单位为分,可以做部分退款
     */
    @XStreamAlias(value = "refund_fee_0")
    private Integer refundFee;

    /**
     * 退款状态：
     * SUCCESS—退款成功
     * REFUNDCLOSE—退款关闭。
     * PROCESSING—退款处理中
     * CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。$n为下标，从0开始编号。
     */
    @XStreamAlias(value = "refund_status_0")
    private String refundStatus;

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
    @XStreamAlias(value = "refund_recv_accout_0")
    private String refundRecvAccount;

    /**
     * 退款成功时间，当退款状态为退款成功时有返回。$n为下标，从0开始编号。
     */
    @XStreamAlias(value = "refund_success_time_0")
    private String refundSuccessTime;

    /**
     * 转换实体
     *
     * @return
     */
    public WechatPaymentQueryRefundResult toResult() {
        return WechatPaymentQueryRefundResult.builder()
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
                .totalFee(this.totalFee)
                .cashFee(this.cashFee)
                .refundCount(this.refundCount)
                .outRefundNo(this.outRefundNo)
                .refundId(this.refundId)
                .refundFee(this.refundFee)
                .refundStatus(this.refundStatus)
                .refundRecvAccount(this.refundRecvAccount)
                .refundSuccessTime(StrUtil.isNotBlank(this.refundSuccessTime) ? LocalDateTimeUtil.parseLocalDateTime(this.refundSuccessTime) : null)
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
