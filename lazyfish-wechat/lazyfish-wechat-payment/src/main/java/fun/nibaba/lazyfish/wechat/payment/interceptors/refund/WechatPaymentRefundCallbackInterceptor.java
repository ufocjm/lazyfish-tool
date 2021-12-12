package fun.nibaba.lazyfish.wechat.payment.interceptors.refund;

import fun.nibaba.lazyfish.wechat.payment.interceptors.WechatPaymentInterceptor;
import fun.nibaba.lazyfish.wechat.payment.model.refund.WechatPaymentRefundCallBackResult;

/**
 * 微信支付-退款回调-拦截器
 *
 * @author chenjiamin
 * @date 2021/5/14 11:28 下午
 */
public interface WechatPaymentRefundCallbackInterceptor extends WechatPaymentInterceptor<String, WechatPaymentRefundCallBackResult> {

}
