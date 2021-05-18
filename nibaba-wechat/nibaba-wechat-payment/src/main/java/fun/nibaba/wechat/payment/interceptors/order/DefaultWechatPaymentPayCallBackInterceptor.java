package fun.nibaba.wechat.payment.interceptors.order;

import fun.nibaba.wechat.payment.model.order.WechatPaymentPayCallBackResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenjiamin
 * @description 微信查询订单拦截器
 * @date 2021/5/14 11:29 下午
 */
@Slf4j
public class DefaultWechatPaymentPayCallBackInterceptor implements WechatPaymentPayCallbackInterceptor {
    @Override
    public void processBefore(String xmlBody) {
        log.debug("进入[微信查询订单]默认前置过滤器,传入参数:[{}]", xmlBody);
    }

    @Override
    public void processAfter(String xmlBody, WechatPaymentPayCallBackResult result) {
        log.debug("进入[微信查询订单]默认后置过滤器,传入参数:[{}],返回结果:[{}]", xmlBody, result.toString());
    }
}
