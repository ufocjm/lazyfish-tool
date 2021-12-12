package fun.nibaba.lazyfish.wechat.payment.model.order;

import cn.hutool.core.util.StrUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import fun.nibaba.lazyfish.wechat.payment.enums.ResultCode;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentResponse;
import fun.nibaba.lazyfish.wechat.payment.utils.LocalDateTimeUtil;
import lombok.Data;

/**
 * 微信支付-查询订单-请求返回结果
 *
 * @author chenjiamin
 * @date 2021/5/14 11:49 下午
 */
@Data
public class WechatPaymentQueryOrderResponse extends WechatPaymentResponse {

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
     * 用户在商户appid下的唯一标识
     */
    @XStreamAlias(value = "openid")
    private String openid;

    /**
     * 用户是否关注公众账号，Y-关注，N-未关注
     */

    @XStreamAlias(value = "is_subscribe")
    private String isSubscribe;

    /**
     * 交易类型
     * JSAPI -JSAPI支付
     * NATIVE -Native支付
     * APP -APP支付
     */
    @XStreamAlias(value = "trade_type")
    private String tradeType;

    /**
     * 交易状态 {@link fun.nibaba.lazyfish.wechat.payment.enums.TradeState}
     * SUCCESS--支付成功
     * REFUND--转入退款
     * NOTPAY--未支付
     * CLOSED--已关闭
     * REVOKED--已撤销(刷卡支付)
     * USERPAYING--用户支付中
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     * ACCEPT--已接收，等待扣款
     * 支付状态机请见下单API页面
     */
    @XStreamAlias(value = "trade_state")
    private String tradeState;

    /**
     * 付款银行 {@link fun.nibaba.lazyfish.wechat.payment.enums.BankType}
     */
    @XStreamAlias(value = "bank_type")
    private String bankType;

    /**
     * 标价金额
     * 订单总金额，单位为分
     */
    @XStreamAlias(value = "total_fee")
    private Integer totalFee;

    /**
     * 现金支付金额订单现金支付金额
     */
    @XStreamAlias(value = "cash_fee")
    private String cashFee;

    /**
     * 代金券金额
     * “代金券”金额<=订单金额，订单金额-“代金券”金额=现金支付金额
     */
    @XStreamAlias(value = "coupon_fee")
    private Integer couponFee;

    /**
     * 代金券使用数量
     * todo 具体还有代金券的列表等下一版本
     */
    @XStreamAlias(value = "coupon_count")
    private Integer couponFount;

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
     * 附加数据
     * 原样返回
     */
    @XStreamAlias(value = "attach")
    private String attach;

    /**
     * 支付完成时间
     * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。
     */
    @XStreamAlias(value = "time_end")
    private String timeEnd;

    /**
     * 交易状态描述
     * 例:支付失败，请重新下单支付
     * 对当前查询订单状态的描述和下一步操作的指引
     */
    @XStreamAlias(value = "trade_state_desc")
    private String tradeStateDesc;

    /**
     * 转换实体
     *
     * @return
     */
    public WechatPaymentQueryOrderResult toResult() {
        return WechatPaymentQueryOrderResult.builder()
                .returnCode(this.returnCode)
                .returnMsg(this.returnMsg)
                .appid(this.appid)
                .mchId(this.mchId)
                .nonceStr(this.nonceStr)
                .sign(this.sign)
                .resultCode(this.resultCode)
                .errCode(this.errCode)
                .errCodeDes(this.errCodeDes)
                .openid(this.openid)
                .isSubscribe(this.isSubscribe)
                .tradeType(this.tradeType)
                .tradeState(this.tradeState)
                .bankType(this.bankType)
                .totalFee(this.totalFee)
                .cashFee(this.cashFee)
                .couponFee(this.couponFee)
                .couponFount(this.couponFount)
                .transactionId(this.transactionId)
                .outTradeNo(this.outTradeNo)
                .attach(this.attach)
                .timeEnd(StrUtil.isNotBlank(this.timeEnd) ? LocalDateTimeUtil.parsePureLocalDateTime(this.timeEnd) : null)
                .tradeStateDesc(this.tradeStateDesc)
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
