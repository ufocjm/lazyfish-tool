package fun.nibaba.lazyfish.wechat.payment.model.refund;

import cn.hutool.core.util.StrUtil;
import fun.nibaba.lazyfish.wechat.payment.exceptions.WechatPaymentParamsInValidException;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentOrderParams;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 微信支付-创建退款-参数
 *
 * @author chenjiamin
 * @date 2021/5/21 23:26 下午
 */
@ToString
@Getter
public class WechatPaymentCreateRefundParams extends WechatPaymentOrderParams {

    /**
     * 微信支付订单号
     * 微信生成的订单号，在支付通知中有返回
     */
    private final String transactionId;

    /**
     * 商户退款单号
     * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    @NotBlank(message = "商户退款单号不能为空")
    private final String outRefundNo;

    /**
     * 订单总金额，单位为分
     */
    @Min(value = 1, message = "订单总金额必须大于0")
    private final int totalFee;

    /**
     * 退款金额
     * 退款总金额，订单总金额，单位为分，只能为整数
     */
    @Min(value = 1, message = "退款金额必须大于0")
    private final int refundFee;

    /**
     * 退款原因
     * 若商户传入，会在下发给用户的退款消息中体现退款原因
     * <p>
     * 注意：若订单退款金额≤1元，且属于部分退款，则不会在退款消息中体现退款原因
     */
    private final String refundDesc;


    /**
     * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    @NotBlank(message = "通知回调地址不能为空")
    private final String notifyUrl;

    @Builder
    public WechatPaymentCreateRefundParams(String nonceStr,
                                           String outTradeNo,
                                           String transactionId,
                                           String outRefundNo,
                                           int totalFee,
                                           int refundFee,
                                           String refundDesc,
                                           String notifyUrl) {
        super(nonceStr, outTradeNo);
        if (StrUtil.isBlank(transactionId) && StrUtil.isBlank(outTradeNo)) {
            throw new WechatPaymentParamsInValidException("微信支付订单号[transactionId]或者商户订单号[outTradeNo]至少一个不能为空");
        }
        this.transactionId = transactionId;
        this.outRefundNo = outRefundNo;
        this.totalFee = totalFee;
        this.refundFee = refundFee;
        this.refundDesc = refundDesc;
        this.notifyUrl = notifyUrl;
    }
}
