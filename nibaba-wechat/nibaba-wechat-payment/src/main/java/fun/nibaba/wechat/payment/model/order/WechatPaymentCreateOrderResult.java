package fun.nibaba.wechat.payment.model.order;

import fun.nibaba.wechat.payment.model.WechatPaymentResult;
import lombok.Builder;
import lombok.Getter;

/**
 * @author chenjiamin
 * @description 微信支付 返回结果
 * @date 2021/5/14 11:49 下午
 */
@Getter
public class WechatPaymentCreateOrderResult extends WechatPaymentResult {

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
     * 交易类型 {@link fun.nibaba.wechat.payment.enums.TradeType}
     * JSAPI -JSAPI支付
     * NATIVE -Native支付
     * APP -APP支付
     */
    private final String tradeType;

    /**
     * 预支付交易会话标识
     * 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    private final String prepayId;

    /**
     * 二维码链接
     * trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。
     */
    private final String codeUrl;

    @Builder
    public WechatPaymentCreateOrderResult(String returnCode,
                                          String returnMsg,
                                          String appid,
                                          String mchId,
                                          String nonceStr,
                                          String sign,
                                          String resultCode,
                                          String errCode,
                                          String errCodeDes,
                                          String tradeType,
                                          String prepayId,
                                          String codeUrl) {
        super(returnCode, returnMsg, appid, mchId, nonceStr, sign);
        this.resultCode = resultCode;
        this.errCode = errCode;
        this.errCodeDes = errCodeDes;
        this.tradeType = tradeType;
        this.prepayId = prepayId;
        this.codeUrl = codeUrl;
    }
}
