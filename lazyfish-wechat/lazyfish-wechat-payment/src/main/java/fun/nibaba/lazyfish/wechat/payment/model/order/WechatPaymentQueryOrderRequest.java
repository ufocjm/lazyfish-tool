package fun.nibaba.lazyfish.wechat.payment.model.order;

import com.thoughtworks.xstream.XStream;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentRequest;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentSign;

/**
 * 微信支付-查询订单-请求
 *
 * @author chenjiamin
 * @date 2021/5/15 4:26 下午
 */
public class WechatPaymentQueryOrderRequest extends WechatPaymentRequest<WechatPaymentQueryOrderResponse> {

    /**
     * 创建统一订单接口
     */
    private static final String QUERY_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

    private static final XStream XSTREAM;

    private static final String WECHAT_PAYMENT_QUERY_ORDER_RESPONSE_ALIAS = "xml";

    static {
        XSTREAM = new XStream();
        XSTREAM.autodetectAnnotations(true);
        XSTREAM.ignoreUnknownElements();
        XSTREAM.alias(WECHAT_PAYMENT_QUERY_ORDER_RESPONSE_ALIAS, WechatPaymentQueryOrderResponse.class);
    }

    public WechatPaymentQueryOrderRequest(WechatPaymentSign wechatPaymentSign) {
        super(wechatPaymentSign, QUERY_ORDER_URL, XSTREAM);
    }

}
