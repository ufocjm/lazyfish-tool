package fun.nibaba.lazyfish.wechat.payment.interceptors.refund;

import fun.nibaba.lazyfish.wechat.payment.interceptors.WechatPaymentInterceptor;
import fun.nibaba.lazyfish.wechat.payment.model.refund.WechatPaymentQueryRefundParams;
import fun.nibaba.lazyfish.wechat.payment.model.refund.WechatPaymentQueryRefundResult;

/**
 * 微信支付-查询退款-拦截器
 *
 * @author chenjiamin
 * @date 2021/5/14 11:28 下午
 */
public interface WechatPaymentQueryRefundInterceptor extends WechatPaymentInterceptor<WechatPaymentQueryRefundParams, WechatPaymentQueryRefundResult> {

}
