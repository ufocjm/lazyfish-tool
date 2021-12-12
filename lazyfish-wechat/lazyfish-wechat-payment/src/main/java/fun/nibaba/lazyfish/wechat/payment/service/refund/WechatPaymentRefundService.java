package fun.nibaba.lazyfish.wechat.payment.service.refund;

import fun.nibaba.lazyfish.wechat.payment.model.refund.*;
import fun.nibaba.lazyfish.wechat.payment.properties.WechatPaymentCertProperties;
import fun.nibaba.lazyfish.wechat.payment.properties.WechatPaymentProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 微信支付-退款服务
 *
 * @author chenjiamin
 * @date 2021/5/14 6:16 下午
 */
@Validated
public interface WechatPaymentRefundService {

    /**
     * 微信支付-创建退款
     *
     * @param params     创建退款所需的参数
     * @param properties 微信支付参数
     * @return 返回结果
     */
    WechatPaymentCreateRefundResult createRefund(@Valid WechatPaymentCreateRefundParams params, @Valid WechatPaymentCertProperties properties);

    /**
     * 微信支付-查询退款订单
     *
     * @param params     查询订单所需的参数
     * @param properties 微信支付参数
     * @return 返回结果
     */
    WechatPaymentQueryRefundResult queryRefund(@Valid WechatPaymentQueryRefundParams params, @Valid WechatPaymentProperties properties);


    /**
     * 微信支付-退款回调
     *
     * @param xmlBody 从request中获取的到xml消息体
     * @return 返回结果
     */
    WechatPaymentRefundCallBackResult callBack(@NotBlank(message = "回调消息体不能为空") String xmlBody, @Valid WechatPaymentProperties properties);

}
