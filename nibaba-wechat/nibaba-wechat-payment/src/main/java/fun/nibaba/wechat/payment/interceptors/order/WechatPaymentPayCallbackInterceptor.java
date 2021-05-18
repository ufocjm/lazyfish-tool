package fun.nibaba.wechat.payment.interceptors.order;

import fun.nibaba.wechat.payment.interceptors.WechatPaymentInterceptor;
import fun.nibaba.wechat.payment.model.order.WechatPaymentPayCallBackResult;

/**
 * @author chenjiamin
 * @description 微信支付后回调拦截器接口
 * @date 2021/5/14 11:28 下午
 */
public interface WechatPaymentPayCallbackInterceptor extends WechatPaymentInterceptor<String, WechatPaymentPayCallBackResult> {

}
