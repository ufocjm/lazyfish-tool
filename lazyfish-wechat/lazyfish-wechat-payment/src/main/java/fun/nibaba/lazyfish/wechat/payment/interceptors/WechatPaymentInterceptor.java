package fun.nibaba.lazyfish.wechat.payment.interceptors;

/**
 * 微信支付-拦截器接口
 *
 * @author chenjiamin
 * @date 2021/5/14 11:27 下午
 */
public interface WechatPaymentInterceptor<Params, Result> {

    /**
     * 前置过滤器
     *
     * @param params 参数
     */
    void processBefore(Params params);


    /**
     * 后置过滤器
     *
     * @param params 参数
     * @param result 返回值
     */
    void processAfter(Params params, Result result);

}
