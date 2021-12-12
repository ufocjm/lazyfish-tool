package fun.nibaba.lazyfish.wechat.payment.interceptors.order;

import fun.nibaba.lazyfish.wechat.payment.model.order.WechatPaymentCreateOrderParams;
import fun.nibaba.lazyfish.wechat.payment.model.order.WechatPaymentCreateOrderResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信支付-创建订单拦截器
 *
 * @author chenjiamin
 * @date 2021/5/14 11:29 下午
 */
@Slf4j
public class DefaultWechatPaymentCreateOrderInterceptor implements WechatPaymentCreateOrderInterceptor {

    @Override
    public void processBefore(WechatPaymentCreateOrderParams params) {
        log.debug("进入[微信创建订单]默认前置过滤器,传入参数:[{}]", params.toString());
    }

    @Override
    public void processAfter(WechatPaymentCreateOrderParams params, WechatPaymentCreateOrderResult result) {
        log.debug("进入[微信创建订单]默认后置过滤器,传入参数:[{}],返回结果:[{}]", params.toString(), result.toString());
    }
}
