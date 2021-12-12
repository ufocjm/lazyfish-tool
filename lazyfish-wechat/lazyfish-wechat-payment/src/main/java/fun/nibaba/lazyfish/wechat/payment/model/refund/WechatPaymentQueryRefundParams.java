package fun.nibaba.lazyfish.wechat.payment.model.refund;

import cn.hutool.core.util.StrUtil;
import fun.nibaba.lazyfish.wechat.payment.exceptions.WechatPaymentParamsInValidException;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentOrderParams;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 微信支付-查询退款-参数
 *
 * @author chenjiamin
 * @date 2021/5/17 3:21 下午
 */
@ToString
@Getter
public class WechatPaymentQueryRefundParams extends WechatPaymentOrderParams {

    /**
     * 微信的订单号
     */
    private final String transactionId;

    /**
     * 商户退款单号
     */
    private final String outRefundNo;

    /**
     * 微信退款单号,建议优先使用
     */
    private final String refundId;


    @Builder
    public WechatPaymentQueryRefundParams(String nonceStr, String outTradeNo, String transactionId, String outRefundNo, String refundId) {
        super(nonceStr, outTradeNo);
        if (StrUtil.isBlank(outTradeNo)
                && StrUtil.isBlank(transactionId)
                && StrUtil.isBlank(outRefundNo)
                && StrUtil.isBlank(refundId)) {
            throw new WechatPaymentParamsInValidException("商户订单号/微信订单号/商户退款单号/微信退款单号不能同时为空");
        }
        this.transactionId = transactionId;
        this.outRefundNo = outRefundNo;
        this.refundId = refundId;
    }
}
