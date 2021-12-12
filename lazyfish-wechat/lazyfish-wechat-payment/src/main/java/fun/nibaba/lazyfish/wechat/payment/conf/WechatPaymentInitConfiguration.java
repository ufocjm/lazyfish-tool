package fun.nibaba.lazyfish.wechat.payment.conf;

import fun.nibaba.lazyfish.wechat.payment.interceptors.order.*;
import fun.nibaba.lazyfish.wechat.payment.interceptors.refund.*;
import fun.nibaba.lazyfish.wechat.payment.service.order.WechatPaymentOrderService;
import fun.nibaba.lazyfish.wechat.payment.service.order.WechatPaymentOrderServiceImpl;
import fun.nibaba.lazyfish.wechat.payment.service.refund.WechatPaymentRefundService;
import fun.nibaba.lazyfish.wechat.payment.service.refund.WechatPaymentRefundServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付-初始化配置
 *
 * @author chenjiamin
 * @date 2021/5/14 10:34 下午
 */
@Configuration
public class WechatPaymentInitConfiguration {

    /**
     * 微信支付-初始化订单配置
     */
    @Configuration
    public static class WechatPaymentInitOrderInterceptor {

        /**
         * 微信支付-声明订单业务实现类
         *
         * @param wechatPaymentCreateOrderInterceptor 声明创建订单拦截器
         * @param wechatPaymentQueryOrderInterceptor  声明订单查询拦截器
         * @param wechatPaymentPayCallbackInterceptor 声明支付回调拦截器
         * @return 订单业务实现类
         */
        @ConditionalOnMissingBean(value = WechatPaymentOrderService.class)
        @Bean
        public WechatPaymentOrderService wechatPaymentOrderService(WechatPaymentCreateOrderInterceptor wechatPaymentCreateOrderInterceptor,
                                                                   WechatPaymentQueryOrderInterceptor wechatPaymentQueryOrderInterceptor,
                                                                   WechatPaymentPayCallbackInterceptor wechatPaymentPayCallbackInterceptor) {
            return new WechatPaymentOrderServiceImpl(wechatPaymentCreateOrderInterceptor, wechatPaymentQueryOrderInterceptor, wechatPaymentPayCallbackInterceptor);
        }

        /**
         * 微信支付-声明创建订单拦截器
         *
         * @return 订单拦截器
         */
        @ConditionalOnMissingBean(value = WechatPaymentCreateOrderInterceptor.class)
        @Bean
        public WechatPaymentCreateOrderInterceptor wechatPaymentCreateOrderInterceptor() {
            return new DefaultWechatPaymentCreateOrderInterceptor();
        }

        /**
         * 微信支付-声明订单查询拦截器
         *
         * @return 订单查询拦截器
         */
        @ConditionalOnMissingBean(value = WechatPaymentQueryOrderInterceptor.class)
        @Bean
        public WechatPaymentQueryOrderInterceptor wechatPaymentQueryOrderInterceptor() {
            return new DefaultWechatPaymentQueryOrderInterceptor();
        }

        /**
         * 微信支付-声明支付回调拦截器
         *
         * @return 支付回调拦截器
         */
        @ConditionalOnMissingBean(value = WechatPaymentPayCallbackInterceptor.class)
        @Bean
        public WechatPaymentPayCallbackInterceptor wechatPaymentPayCallbackInterceptor() {
            return new DefaultWechatPaymentPayCallBackInterceptor();
        }

    }

    /**
     * 微信支付-初始化退款配置
     */
    @Configuration
    public static class WechatPaymentInitRefundInterceptor {

        /**
         * 微信支付-声明退款业务实现类
         *
         * @param wechatPaymentCreateRefundInterceptor   创建退款拦截器
         * @param wechatPaymentQueryRefundInterceptor    查询退款拦截器
         * @param wechatPaymentRefundCallbackInterceptor 退款回调拦截器
         * @return 退款业务实现类
         */
        @ConditionalOnMissingBean(value = WechatPaymentRefundService.class)
        @Bean
        public WechatPaymentRefundService wechatPaymentRefundService(WechatPaymentCreateRefundInterceptor wechatPaymentCreateRefundInterceptor,
                                                                     WechatPaymentQueryRefundInterceptor wechatPaymentQueryRefundInterceptor,
                                                                     WechatPaymentRefundCallbackInterceptor wechatPaymentRefundCallbackInterceptor) {
            return new WechatPaymentRefundServiceImpl(wechatPaymentCreateRefundInterceptor, wechatPaymentQueryRefundInterceptor, wechatPaymentRefundCallbackInterceptor);
        }

        /**
         * 微信支付-声明创建退款拦截器
         *
         * @return 创建退款拦截器
         */
        @ConditionalOnMissingBean(value = WechatPaymentCreateRefundInterceptor.class)
        @Bean
        public WechatPaymentCreateRefundInterceptor wechatPaymentCreateRefundInterceptor() {
            return new DefaultWechatPaymentCreateRefundInterceptor();
        }

        /**
         * 微信支付-声明查询退款拦截器
         *
         * @return 查询退款拦截器
         */
        @ConditionalOnMissingBean(value = WechatPaymentQueryRefundInterceptor.class)
        @Bean
        public WechatPaymentQueryRefundInterceptor wechatPaymentQueryRefundInterceptor() {
            return new DefaultWechatPaymentQueryRefundInterceptor();
        }

        /**
         * 微信支付-声明退款回调拦截器
         *
         * @return 退款回调拦截器
         */
        @ConditionalOnMissingBean(value = WechatPaymentRefundCallbackInterceptor.class)
        @Bean
        public WechatPaymentRefundCallbackInterceptor wechatPaymentRefundCallbackInterceptor() {
            return new DefaultWechatPaymentRefundCallBackInterceptor();
        }

    }

}
