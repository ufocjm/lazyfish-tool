package fun.nibaba.lazyfish.wechat.payment.model.order;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import fun.nibaba.lazyfish.wechat.payment.enums.ResultCode;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentResponse;
import lombok.Data;

/**
 * 微信支付-创建订单-请求返回结果
 *
 * @author chenjiamin
 * @date 2021/5/14 11:49 下午
 */
@Data
public class WechatPaymentCreateOrderResponse extends WechatPaymentResponse {

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
     * 交易类型 {@link fun.nibaba.lazyfish.wechat.payment.enums.TradeType}
     * JSAPI -JSAPI支付
     * NATIVE -Native支付
     * APP -APP支付
     */
    @XStreamAlias(value = "trade_type")
    private String tradeType;

    /**
     * 预支付交易会话标识
     * 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    @XStreamAlias(value = "prepay_id")
    private String prepayId;

    /**
     * 二维码链接
     * trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。
     */
    @XStreamAlias(value = "code_url")
    private String codeUrl;


    /**
     * 转换为返回结果
     *
     * @return
     */
    public WechatPaymentCreateOrderResult toResult() {
        return WechatPaymentCreateOrderResult.builder()
                .returnCode(this.returnCode)
                .returnMsg(this.returnMsg)
                .appid(this.appid)
                .mchId(this.mchId)
                .nonceStr(this.nonceStr)
                .sign(this.sign)
                .resultCode(this.resultCode)
                .errCode(this.errCode)
                .errCodeDes(this.errCodeDes)
                .tradeType(this.tradeType)
                .prepayId(this.prepayId)
                .codeUrl(this.codeUrl)
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
