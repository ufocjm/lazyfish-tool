package fun.nibaba.lazyfish.wechat.payment.interceptors.refund;

import fun.nibaba.lazyfish.wechat.payment.interceptors.WechatPaymentInterceptor;
import fun.nibaba.lazyfish.wechat.payment.model.refund.WechatPaymentCreateRefundParams;
import fun.nibaba.lazyfish.wechat.payment.model.refund.WechatPaymentCreateRefundResult;

/**
 * 微信支付-创建退款-拦截器
 *
 * @author chenjiamin
 * @date 2021/5/14 11:28 下午
 */
public interface WechatPaymentCreateRefundInterceptor extends WechatPaymentInterceptor<WechatPaymentCreateRefundParams, WechatPaymentCreateRefundResult> {

}
