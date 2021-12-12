package fun.nibaba.lazyfish.wechat.payment.interceptors.order;

import fun.nibaba.lazyfish.wechat.payment.interceptors.WechatPaymentInterceptor;
import fun.nibaba.lazyfish.wechat.payment.model.order.WechatPaymentQueryOrderParams;
import fun.nibaba.lazyfish.wechat.payment.model.order.WechatPaymentQueryOrderResult;

/**
 * 微信支付-查询订单拦截器接口
 *
 * @author chenjiamin
 * @date 2021/5/14 11:28 下午
 */
public interface WechatPaymentQueryOrderInterceptor extends WechatPaymentInterceptor<WechatPaymentQueryOrderParams, WechatPaymentQueryOrderResult> {

}
