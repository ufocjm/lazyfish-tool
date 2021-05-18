package fun.nibaba.wechat.payment.model.order;

import cn.hutool.core.util.StrUtil;
import fun.nibaba.wechat.payment.exceptions.WechatPaymentParamsInValidException;
import fun.nibaba.wechat.payment.model.WechatPaymentOrderParams;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author chenjiamin
 * @description TODO
 * @date 2021/5/17 3:21 下午
 */
@ToString
@Getter
public class WechatPaymentQueryOrderParams extends WechatPaymentOrderParams {

    /**
     * 微信的订单号，建议优先使用
     */
    private final String transactionId;

    @Builder
    public WechatPaymentQueryOrderParams(String nonceStr, String outTradeNo, String transactionId) {
        super(nonceStr, outTradeNo);
        if (StrUtil.isBlank(outTradeNo) && StrUtil.isBlank(transactionId)) {
            throw new WechatPaymentParamsInValidException("商户订单号和微信订单号不能同时为空");
        }
        this.transactionId = transactionId;
    }
}
