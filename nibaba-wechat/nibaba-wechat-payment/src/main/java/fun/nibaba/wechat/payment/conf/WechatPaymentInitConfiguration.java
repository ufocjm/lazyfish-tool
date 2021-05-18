package fun.nibaba.wechat.payment.conf;

import fun.nibaba.wechat.payment.interceptors.order.*;
import fun.nibaba.wechat.payment.service.order.WechatPaymentOrderService;
import fun.nibaba.wechat.payment.service.order.WechatPaymentOrderServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenjiamin
 * @description 微信支付初始化配置类
 * @date 2021/5/14 10:34 下午
 */
@Configuration
public class WechatPaymentInitConfiguration {

    @ConditionalOnMissingBean(value = WechatPaymentOrderService.class)
    @Bean
    public WechatPaymentOrderService wechatPaymentOrderService(WechatPaymentCreateOrderInterceptor wechatPaymentCreateOrderInterceptor,
                                                               WechatPaymentQueryOrderInterceptor wechatPaymentQueryOrderInterceptor,
                                                               WechatPaymentPayCallbackInterceptor wechatPaymentPayCallbackInterceptor) {
        return new WechatPaymentOrderServiceImpl(wechatPaymentCreateOrderInterceptor, wechatPaymentQueryOrderInterceptor, wechatPaymentPayCallbackInterceptor);
    }

    @ConditionalOnMissingBean(value = WechatPaymentCreateOrderInterceptor.class)
    @Bean
    public WechatPaymentCreateOrderInterceptor wechatPaymentCreateOrderInterceptor() {
        return new DefaultWechatPaymentCreateOrderInterceptor();
    }

    @ConditionalOnMissingBean(value = WechatPaymentQueryOrderInterceptor.class)
    @Bean
    public WechatPaymentQueryOrderInterceptor wechatPaymentQueryOrderInterceptor() {
        return new DefaultWechatPaymentQueryOrderInterceptor();
    }

    @ConditionalOnMissingBean(value = WechatPaymentPayCallbackInterceptor.class)
    @Bean
    public WechatPaymentPayCallbackInterceptor wechatPaymentPayCallbackInterceptor() {
        return new DefaultWechatPaymentPayCallBackInterceptor();
    }

}
