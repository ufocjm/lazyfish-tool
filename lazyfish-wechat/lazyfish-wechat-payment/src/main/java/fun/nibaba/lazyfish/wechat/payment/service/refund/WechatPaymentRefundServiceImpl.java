package fun.nibaba.lazyfish.wechat.payment.service.refund;

import fun.nibaba.lazyfish.wechat.payment.interceptors.refund.WechatPaymentCreateRefundInterceptor;
import fun.nibaba.lazyfish.wechat.payment.interceptors.refund.WechatPaymentQueryRefundInterceptor;
import fun.nibaba.lazyfish.wechat.payment.interceptors.refund.WechatPaymentRefundCallbackInterceptor;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentSign;
import fun.nibaba.lazyfish.wechat.payment.model.refund.*;
import fun.nibaba.lazyfish.wechat.payment.properties.WechatPaymentCertProperties;
import fun.nibaba.lazyfish.wechat.payment.properties.WechatPaymentProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 微信支付-退款服务-实现
 *
 * @author chenjiamin
 * @date 2021/5/21 11:18 下午
 */
@Slf4j
@AllArgsConstructor
public class WechatPaymentRefundServiceImpl implements WechatPaymentRefundService {

    private final WechatPaymentCreateRefundInterceptor wechatPaymentCreateRefundInterceptor;

    private final WechatPaymentQueryRefundInterceptor wechatPaymentQueryRefundInterceptor;

    private final WechatPaymentRefundCallbackInterceptor wechatPaymentRefundCallbackInterceptor;

    /**
     * 微信支付-创建退款
     *
     * @param params     创建退款所需的参数
     * @param properties 微信支付参数
     * @return
     */
    @Override
    public WechatPaymentCreateRefundResult createRefund(@Valid WechatPaymentCreateRefundParams params, @Valid WechatPaymentCertProperties properties) {
        wechatPaymentCreateRefundInterceptor.processBefore(params);

        log.debug("开始-请求微信支付创建退款");
        WechatPaymentCreateRefundRequest request = new WechatPaymentCreateRefundRequest(params, properties);
        WechatPaymentCreateRefundResponse response = request.request();
        log.debug("结束-请求微信支付创建退款");
        WechatPaymentCreateRefundResult result = response.toResult();

        wechatPaymentCreateRefundInterceptor.processAfter(params, result);

        return result;
    }

    /**
     * 微信支付-查询退款订单
     *
     * @param params     查询订单所需的参数
     * @param properties 微信支付参数
     * @return
     */
    @Override
    public WechatPaymentQueryRefundResult queryRefund(@Valid WechatPaymentQueryRefundParams params, @Valid WechatPaymentProperties properties) {
        wechatPaymentQueryRefundInterceptor.processBefore(params);

        log.debug("开始-请求微信支付查询退款");
        WechatPaymentQueryRefundRequest request = new WechatPaymentQueryRefundRequest(new WechatPaymentSign(params, properties));
        WechatPaymentQueryRefundResponse response = request.request();
        log.debug("结束-请求微信支付查询退款");
        WechatPaymentQueryRefundResult result = response.toResult();

        wechatPaymentQueryRefundInterceptor.processAfter(params, result);
        return null;
    }

    /**
     * 微信支付-退款回调
     *
     * @param xmlBody 从request中获取的到xml消息体
     * @return
     */
    @Override
    public WechatPaymentRefundCallBackResult callBack(@NotBlank(message = "回调消息体不能为空") String xmlBody, @Valid WechatPaymentProperties properties) {
        wechatPaymentRefundCallbackInterceptor.processBefore(xmlBody);

        log.debug("开始-转换微信退款消息体");
        WechatPaymentRefundCallBackBody body = new WechatPaymentRefundCallBackBody(xmlBody);
        WechatPaymentRefundCallBackEncryptionResponse encryptionResponse = body.build();
        WechatPaymentRefundCallBackDecryptionResponse decryptionResponse = new WechatPaymentRefundCallBackDecryptionResponse(encryptionResponse.getReqInfo(), properties.getMchSecret()).build();
        log.debug("结束-转换微信退款消息体");

        WechatPaymentRefundCallBackResult result = decryptionResponse.toResult(encryptionResponse);

        wechatPaymentRefundCallbackInterceptor.processAfter(xmlBody, result);
        return result;
    }
}
