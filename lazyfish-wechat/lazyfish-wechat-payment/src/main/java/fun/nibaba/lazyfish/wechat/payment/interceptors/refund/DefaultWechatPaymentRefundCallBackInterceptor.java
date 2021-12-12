package fun.nibaba.lazyfish.wechat.payment.interceptors.refund;

import fun.nibaba.lazyfish.wechat.payment.model.refund.WechatPaymentRefundCallBackResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信退款-退款回调-拦截器默认实现
 *
 * @author chenjiamin
 * @date 2021/5/14 11:29 下午
 */
@Slf4j
public class DefaultWechatPaymentRefundCallBackInterceptor implements WechatPaymentRefundCallbackInterceptor {
    @Override
    public void processBefore(String xmlBody) {
        log.debug("进入[微信退款回调]默认前置过滤器,传入参数:[{}]", xmlBody);
    }

    @Override
    public void processAfter(String xmlBody, WechatPaymentRefundCallBackResult result) {
        log.debug("进入[微信退款回调]默认后置过滤器,传入参数:[{}],返回结果:[{}]", xmlBody, result.toString());
    }
}
