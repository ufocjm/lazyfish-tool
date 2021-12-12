package fun.nibaba.lazyfish.wechat.payment.service.order;

import fun.nibaba.lazyfish.wechat.payment.interceptors.order.WechatPaymentCreateOrderInterceptor;
import fun.nibaba.lazyfish.wechat.payment.interceptors.order.WechatPaymentPayCallbackInterceptor;
import fun.nibaba.lazyfish.wechat.payment.interceptors.order.WechatPaymentQueryOrderInterceptor;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentSign;
import fun.nibaba.lazyfish.wechat.payment.model.order.*;
import fun.nibaba.lazyfish.wechat.payment.properties.WechatPaymentProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 微信支付-订单服务-实现类
 *
 * @author chenjiamin
 * @date 2021/5/14 10:34 下午
 */
@Slf4j
@AllArgsConstructor
public class WechatPaymentOrderServiceImpl implements WechatPaymentOrderService {

    private final WechatPaymentCreateOrderInterceptor createOrderInterceptor;

    private final WechatPaymentQueryOrderInterceptor queryOrderInterceptor;

    private final WechatPaymentPayCallbackInterceptor payCallbackInterceptor;

    @Override
    public WechatPaymentCreateOrderResult createOrder(WechatPaymentCreateOrderParams params, WechatPaymentProperties properties) {
        createOrderInterceptor.processBefore(params);

        log.debug("开始-请求微信支付创建订单");
        WechatPaymentCreateOrderRequest orderRequest = new WechatPaymentCreateOrderRequest(new WechatPaymentSign(params, properties));
        WechatPaymentCreateOrderResponse response = orderRequest.request();
        log.debug("结束-请求微信支付创建订单");

        WechatPaymentCreateOrderResult result = response.toResult();
        createOrderInterceptor.processAfter(params, result);

        return result;
    }

    @Override
    public WechatPaymentQueryOrderResult queryOrder(@Valid WechatPaymentQueryOrderParams params, @Valid WechatPaymentProperties properties) {
        queryOrderInterceptor.processBefore(params);

        log.debug("开始-请求微信支付查询订单");
        WechatPaymentQueryOrderRequest queryOrderRequest = new WechatPaymentQueryOrderRequest(new WechatPaymentSign(params, properties));
        WechatPaymentQueryOrderResponse response = queryOrderRequest.request();
        log.debug("结束-请求微信支付查询订单");

        WechatPaymentQueryOrderResult result = response.toResult();

        queryOrderInterceptor.processAfter(params, result);
        return result;
    }

    @Override
    public WechatPaymentPayCallBackResult callBack(@NotBlank(message = "回调消息体不能为空") String xmlBody) {
        payCallbackInterceptor.processBefore(xmlBody);

        log.debug("开始-微信支付回调解析开始");
        WechatPaymentPayCallBackBody wechatPaymentPayCallBackBody = new WechatPaymentPayCallBackBody(xmlBody);
        WechatPaymentPayCallBackResponse response = wechatPaymentPayCallBackBody.build();
        log.debug("开始-微信支付回调解析结束");

        WechatPaymentPayCallBackResult result = response.toResult();

        payCallbackInterceptor.processAfter(xmlBody, result);
        return result;
    }
}
