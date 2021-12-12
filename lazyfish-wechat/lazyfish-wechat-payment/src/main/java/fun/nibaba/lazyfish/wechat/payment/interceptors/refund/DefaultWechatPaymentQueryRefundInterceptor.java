package fun.nibaba.lazyfish.wechat.payment.interceptors.refund;

import fun.nibaba.lazyfish.wechat.payment.model.refund.WechatPaymentQueryRefundParams;
import fun.nibaba.lazyfish.wechat.payment.model.refund.WechatPaymentQueryRefundResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信支付-查询退款-拦截器默认实现
 *
 * @author chenjiamin
 * @date 2021/5/14 11:29 下午
 */
@Slf4j
public class DefaultWechatPaymentQueryRefundInterceptor implements WechatPaymentQueryRefundInterceptor {

    @Override
    public void processBefore(WechatPaymentQueryRefundParams params) {
        log.debug("进入[微信支付-查询退款]默认前置过滤器,传入参数:[{}]", params.toString());
    }

    @Override
    public void processAfter(WechatPaymentQueryRefundParams params, WechatPaymentQueryRefundResult result) {
        log.debug("进入[微信支付-查询退款]默认后置过滤器,传入参数:[{}],返回结果:[{}]", params.toString(), result.toString());
    }
}
