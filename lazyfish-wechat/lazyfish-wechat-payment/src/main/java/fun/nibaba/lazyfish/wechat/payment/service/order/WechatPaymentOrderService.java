package fun.nibaba.lazyfish.wechat.payment.service.order;

import fun.nibaba.lazyfish.wechat.payment.model.order.*;
import fun.nibaba.lazyfish.wechat.payment.properties.WechatPaymentProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 微信支付-订单服务
 *
 * @author chenjiamin
 * @date 2021/5/14 6:16 下午
 */
@Validated
public interface WechatPaymentOrderService {

    /**
     * 创建订单
     *
     * @param params     创建订单所需的参数
     * @param properties 微信支付参数
     * @return 返回结果
     */
    WechatPaymentCreateOrderResult createOrder(@Valid WechatPaymentCreateOrderParams params, @Valid WechatPaymentProperties properties);

    /**
     * 查询订单
     *
     * @param params     查询订单所需的参数
     * @param properties 微信支付参数
     * @return 返回结果
     */
    WechatPaymentQueryOrderResult queryOrder(@Valid WechatPaymentQueryOrderParams params, @Valid WechatPaymentProperties properties);

    /**
     * 微信支付后的回调
     *
     * @param xmlBody 从request中获取的到xml消息体
     * @return 返回结果
     */
    WechatPaymentPayCallBackResult callBack(@NotBlank(message = "回调消息体不能为空") String xmlBody);
}
