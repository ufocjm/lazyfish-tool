package fun.nibaba.wechat.payment.interceptors;

/**
 * @author chenjiamin
 * @description 微信支付拦截器接口
 * @date 2021/5/14 11:27 下午
 */
public interface WechatPaymentInterceptor<Params, Result> {

    /**
     * 前置过滤器
     *
     * @param params
     */
    void processBefore(Params params);


    /**
     * 后置过滤器
     *
     * @param params
     * @param result
     */
    void processAfter(Params params, Result result);

}
