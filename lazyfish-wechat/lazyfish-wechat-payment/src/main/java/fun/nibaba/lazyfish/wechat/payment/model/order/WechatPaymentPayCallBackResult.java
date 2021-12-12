package fun.nibaba.lazyfish.wechat.payment.model.order;

import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentResult;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 微信支付-支付回调-返回结果
 *
 * @author chenjiamin
 * @date 2021/5/18 11:28 上午
 */
@ToString
@Getter
public class WechatPaymentPayCallBackResult extends WechatPaymentResult {

    /**
     * 业务结果
     * SUCCESS/FAIL
     */
    private final String resultCode;

    /**
     * 错误代码
     */
    private final String errCode;

    /**
     * 错误代码描述
     */
    private final String errCodeDes;

    /**
     * 用户标识
     */
    private final String openid;

    /**
     * 是否关注公众账号
     * 用户是否关注公众账号，Y-关注，N-未关注
     */
    private final String isSubscribe;

    /**
     * 交易类型 {@link fun.nibaba.lazyfish.wechat.payment.enums.TradeType}
     * 取值为：JSAPI，NATIVE，APP等
     */
    private final String tradeType;

    /**
     * 银行类型 {@link fun.nibaba.lazyfish.wechat.payment.enums.BankType}
     * 采用字符串类型的银行标识
     */
    private final String bankType;

    /**
     * 订单总金额，单位为分
     */
    private final int totalFee;

    /**
     * 现金支付金额订单现金支付金额，详见支付金额
     */
    private final int cashFee;

    /**
     * 现金支付货币类型
     * 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，
     */
    private final String cashFeeType;


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
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    private final String outTradeNo;

    /**
     * 附加数据，原样返回
     */
    private final String attach;

    /**
     * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     */
    private final LocalDateTime timeEnd;

    @Builder
    public WechatPaymentPayCallBackResult(String returnCode,
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
                                          String bankType,
                                          int totalFee,
                                          int cashFee,
                                          String cashFeeType,
                                          Integer couponFee,
                                          Integer couponFount,
                                          String transactionId,
                                          String outTradeNo,
                                          String attach,
                                          LocalDateTime timeEnd) {
        super(returnCode, returnMsg, appid, mchId, nonceStr, sign);
        this.resultCode = resultCode;
        this.errCode = errCode;
        this.errCodeDes = errCodeDes;
        this.openid = openid;
        this.isSubscribe = isSubscribe;
        this.tradeType = tradeType;
        this.bankType = bankType;
        this.totalFee = totalFee;
        this.cashFee = cashFee;
        this.cashFeeType = cashFeeType;
        this.couponFee = couponFee;
        this.couponFount = couponFount;
        this.transactionId = transactionId;
        this.outTradeNo = outTradeNo;
        this.attach = attach;
        this.timeEnd = timeEnd;
    }
}
