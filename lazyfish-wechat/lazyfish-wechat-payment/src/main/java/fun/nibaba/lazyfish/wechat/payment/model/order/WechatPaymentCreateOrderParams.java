package fun.nibaba.lazyfish.wechat.payment.model.order;

import cn.hutool.core.util.StrUtil;
import fun.nibaba.lazyfish.wechat.payment.exceptions.WechatPaymentParamsInValidException;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentOrderParams;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 微信支付-创建订单-参数
 *
 * @author chenjiamin
 * @date 2021/5/14 9:56 下午
 */
@ToString
@Getter
public class WechatPaymentCreateOrderParams extends WechatPaymentOrderParams {

    /**
     * 订单总金额，单位为分
     */
    @Min(value = 1, message = "订单总金额必须大于0")
    private final int totalFee;

    /**
     * 商品简单描述
     */
    @NotBlank(message = "商品简单描述不能为空")
    private final String body;

    /**
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     * required:false
     */
    private final String attach;

    /**
     * 调用微信支付API的机器IP
     */
    @NotBlank(message = "调用微信支付API的机器IP不能为空")
    private final String spbillCreateIp;

    /**
     * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    @NotBlank(message = "通知回调地址不能为空")
    private final String notifyUrl;

    /**
     * 交易类型
     */
    @NotBlank(message = "交易类型不能为空")
    private final String tradeType;

    /**
     * 用户openId
     */
    @NotBlank(message = "用户openId不能为空")
    private final String openid;

    @Builder
    public WechatPaymentCreateOrderParams(String outTradeNo,
                                          String nonceStr,
                                          int totalFee,
                                          String body,
                                          String attach,
                                          String spbillCreateIp,
                                          String notifyUrl,
                                          String tradeType,
                                          String openid) {
        super(nonceStr, outTradeNo);
        if (StrUtil.isBlank(outTradeNo)) {
            throw new WechatPaymentParamsInValidException("订单号不能为空");
        }
        this.totalFee = totalFee;
        this.body = body;
        this.attach = attach;
        this.spbillCreateIp = spbillCreateIp;
        this.notifyUrl = notifyUrl;
        this.tradeType = tradeType;
        this.openid = openid;
    }
}
