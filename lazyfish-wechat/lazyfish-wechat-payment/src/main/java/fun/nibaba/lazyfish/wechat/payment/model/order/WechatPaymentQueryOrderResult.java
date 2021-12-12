package fun.nibaba.lazyfish.wechat.payment.model.order;

import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentResult;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 微信支付-查询订单-返回结果
 *
 * @author chenjiamin
 * @date 2021/5/14 11:49 下午
 */
@ToString
@Getter
public class WechatPaymentQueryOrderResult extends WechatPaymentResult {

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
     * 用户在商户appid下的唯一标识
     */
    private final String openid;

    /**
     * 用户是否关注公众账号，Y-关注，N-未关注
     */

    private final String isSubscribe;

    /**
     * 交易类型
     * JSAPI -JSAPI支付
     * NATIVE -Native支付
     * APP -APP支付
     */
    private final String tradeType;

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
    private final String tradeState;

    /**
     * 付款银行 {@link fun.nibaba.lazyfish.wechat.payment.enums.BankType}
     */
    private final String bankType;

    /**
     * 标价金额
     * 订单总金额，单位为分
     */
    private final Integer totalFee;

    /**
     * 现金支付金额订单现金支付金额
     */
    private final String cashFee;

    /**
     * 代金券金额
     * “代金券”金额<=订单金额，订单金额-“代金券”金额=现金支付金额
     */
    private final Integer couponFee;

    /**
     * 代金券使用数量
     * todo 具体还有代金券的列表等下一版本
     */
    private final Integer couponFount;

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
     * 附加数据
     * 原样返回
     */
    private final String attach;

    /**
     * 支付完成时间
     * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。
     */
    private final LocalDateTime timeEnd;

    /**
     * 交易状态描述
     * 例:支付失败，请重新下单支付
     * 对当前查询订单状态的描述和下一步操作的指引
     */
    private final String tradeStateDesc;

    @Builder
    public WechatPaymentQueryOrderResult(String returnCode,
                                         String returnMsg,
                                         String appid,
                                         String mchId,
                                         String nonceStr,
                                         String sign,
                                         String resultCode,
                                         String errCode,
                                         String errCodeDes,
                                         String openid,
                                         String isSubscribe,
                                         String tradeType,
                                         String tradeState,
                                         String bankType,
                                         Integer totalFee,
                                         String cashFee,
                                         Integer couponFee,
                                         Integer couponFount,
                                         String transactionId,
                                         String outTradeNo,
                                         String attach,
                                         LocalDateTime timeEnd,
                                         String tradeStateDesc) {
        super(returnCode, returnMsg, appid, mchId, nonceStr, sign);
        this.resultCode = resultCode;
        this.errCode = errCode;
        this.errCodeDes = errCodeDes;
        this.openid = openid;
        this.isSubscribe = isSubscribe;
        this.tradeType = tradeType;
        this.tradeState = tradeState;
        this.bankType = bankType;
        this.totalFee = totalFee;
        this.cashFee = cashFee;
        this.couponFee = couponFee;
        this.couponFount = couponFount;
        this.transactionId = transactionId;
        this.outTradeNo = outTradeNo;
        this.attach = attach;
        this.timeEnd = timeEnd;
        this.tradeStateDesc = tradeStateDesc;
    }
}
