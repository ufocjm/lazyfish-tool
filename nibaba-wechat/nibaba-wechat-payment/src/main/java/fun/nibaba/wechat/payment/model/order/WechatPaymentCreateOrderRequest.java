package fun.nibaba.wechat.payment.model.order;

import com.thoughtworks.xstream.XStream;
import fun.nibaba.wechat.payment.model.WechatPaymentRequest;
import fun.nibaba.wechat.payment.model.WechatPaymentSign;

/**
 * 微信支付-创建订单-请求
 *
 * @author chenjiamin
 * @date 2021/5/15 4:26 下午
 */
public class WechatPaymentCreateOrderRequest extends WechatPaymentRequest<WechatPaymentCreateOrderResponse> {

    /**
     * 创建统一订单接口
     */
    private static final String CREATE_UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    private static final XStream XSTREAM;

    private static final String WECHAT_PAYMENT_CREATE_ORDER_RESPONSE_ALIAS = "xml";

    static {
        XSTREAM = new XStream();
        XSTREAM.autodetectAnnotations(true);
        XSTREAM.ignoreUnknownElements();
        XSTREAM.alias(WECHAT_PAYMENT_CREATE_ORDER_RESPONSE_ALIAS, WechatPaymentCreateOrderResponse.class);
    }

    public WechatPaymentCreateOrderRequest(WechatPaymentSign wechatPaymentSign) {
        super(wechatPaymentSign, CREATE_UNIFIED_ORDER_URL, XSTREAM);
    }

}
