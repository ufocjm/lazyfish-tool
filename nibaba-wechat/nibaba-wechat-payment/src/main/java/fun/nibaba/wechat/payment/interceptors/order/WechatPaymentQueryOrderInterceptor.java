package fun.nibaba.wechat.payment.interceptors.order;

import fun.nibaba.wechat.payment.interceptors.WechatPaymentInterceptor;
import fun.nibaba.wechat.payment.model.order.WechatPaymentQueryOrderParams;
import fun.nibaba.wechat.payment.model.order.WechatPaymentQueryOrderResult;

/**
 * @author chenjiamin
 * @description 微信查询订单拦截器接口
 * @date 2021/5/14 11:28 下午
 */
public interface WechatPaymentQueryOrderInterceptor extends WechatPaymentInterceptor<WechatPaymentQueryOrderParams, WechatPaymentQueryOrderResult> {

}
