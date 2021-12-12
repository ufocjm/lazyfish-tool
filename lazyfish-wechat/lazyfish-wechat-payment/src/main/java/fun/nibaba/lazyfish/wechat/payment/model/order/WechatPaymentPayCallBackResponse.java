package fun.nibaba.lazyfish.wechat.payment.model.order;

import cn.hutool.core.util.StrUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentResponse;
import fun.nibaba.lazyfish.wechat.payment.utils.LocalDateTimeUtil;

/**
 * 微信支付-支付回调-解析后实体
 *
 * @author chenjiamin
 * @date 2021/5/18 11:28 上午
 */
public class WechatPaymentPayCallBackResponse extends WechatPaymentResponse {

    /**
     * 业务结果
     * SUCCESS/FAIL
     */
    @XStreamAlias(value = "result_code")
    private String resultCode;

    /**
     * 错误代码
     */
    @XStreamAlias(value = "err_code")
    private String errCode;

    /**
     * 错误代码描述
     */
    @XStreamAlias(value = "err_code_des")
    private String errCodeDes;

    /**
     * 用户标识
     */
    @XStreamAlias(value = "openid")
    private String openid;

    /**
     * 是否关注公众账号
     * 用户是否关注公众账号，Y-关注，N-未关注
     */
    @XStreamAlias(value = "is_subscribe")
    private String isSubscribe;

    /**
     * 交易类型 {@link fun.nibaba.lazyfish.wechat.payment.enums.TradeType}
     * 取值为：JSAPI，NATIVE，APP等
     */
    @XStreamAlias(value = "trade_type")
    private String tradeType;

    /**
     * 银行类型 {@link fun.nibaba.lazyfish.wechat.payment.enums.BankType}
     * 采用字符串类型的银行标识
     */
    @XStreamAlias(value = "bank_type")
    private String bankType;

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
     * 现金支付货币类型
     * 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，
     */
    @XStreamAlias(value = "cash_fee_type")
    private String cashFeeType;


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
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @XStreamAlias(value = "out_trade_no")
    private String outTradeNo;

    /**
     * 附加数据，原样返回
     */
    @XStreamAlias(value = "attach")
    private String attach;

    /**
     * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     */
    @XStreamAlias(value = "time_end")
    private String timeEnd;

    /**
     * 实体转换
     *
     * @return
     */
    public WechatPaymentPayCallBackResult toResult() {
        return WechatPaymentPayCallBackResult.builder()
                .returnCode(this.returnCode)
                .returnMsg(this.returnMsg)
                .appid(this.appid)
                .mchId(this.mchId)
                .nonceStr(this.nonceStr)
                .sign(this.sign)
                .resultCode(this.resultCode)
                .errCode(this.errCode)
                .errCodeDes(this.errCodeDes)
                .totalFee(this.totalFee)
                .cashFee(this.cashFee)
                .cashFeeType(this.cashFeeType)
                .couponFee(this.couponFee)
                .couponFount(this.couponFount)
                .transactionId(this.transactionId)
                .outTradeNo(this.outTradeNo)
                .attach(this.attach)
                .timeEnd(StrUtil.isNotBlank(this.timeEnd) ? LocalDateTimeUtil.parsePureLocalDateTime(this.timeEnd) : null)
                .build();
    }


}
