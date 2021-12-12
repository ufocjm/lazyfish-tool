package fun.nibaba.lazyfish.wechat.payment.interceptors.order;

import fun.nibaba.lazyfish.wechat.payment.interceptors.WechatPaymentInterceptor;
import fun.nibaba.lazyfish.wechat.payment.model.order.WechatPaymentPayCallBackResult;

/**
 * 微信支付-回调拦截器接口
 *
 * @author chenjiamin
 * @date 2021/5/14 11:28 下午
 */
public interface WechatPaymentPayCallbackInterceptor extends WechatPaymentInterceptor<String, WechatPaymentPayCallBackResult> {

}
