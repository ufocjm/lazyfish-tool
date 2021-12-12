package fun.nibaba.lazyfish.wechat.payment.interceptors.order;

import fun.nibaba.lazyfish.wechat.payment.model.order.WechatPaymentQueryOrderParams;
import fun.nibaba.lazyfish.wechat.payment.model.order.WechatPaymentQueryOrderResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信支付-查询订单拦截器
 *
 * @author chenjiamin
 * @date 2021/5/14 11:29 下午
 */
@Slf4j
public class DefaultWechatPaymentQueryOrderInterceptor implements WechatPaymentQueryOrderInterceptor {
    @Override
    public void processBefore(WechatPaymentQueryOrderParams params) {
        log.debug("进入[微信查询订单]默认前置过滤器,传入参数:[{}]", params.toString());
    }

    @Override
    public void processAfter(WechatPaymentQueryOrderParams params, WechatPaymentQueryOrderResult result) {
        log.debug("进入[微信查询订单]默认后置过滤器,传入参数:[{}],返回结果:[{}]", params.toString(), result.toString());
    }
}
