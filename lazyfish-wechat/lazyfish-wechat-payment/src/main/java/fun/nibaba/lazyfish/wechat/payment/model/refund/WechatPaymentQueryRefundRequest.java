package fun.nibaba.lazyfish.wechat.payment.model.refund;

import com.thoughtworks.xstream.XStream;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentRequest;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentSign;

/**
 * 微信支付-查询退款-请求
 *
 * @author chenjiamin
 * @date 2021/5/15 4:26 下午
 */
public class WechatPaymentQueryRefundRequest extends WechatPaymentRequest<WechatPaymentQueryRefundResponse> {

    /**
     * 创建统一订单接口
     */
    private static final String QUERY_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";

    private static final XStream XSTREAM;

    private static final String WECHAT_PAYMENT_QUERY_REFUND_RESPONSE_ALIAS = "xml";

    static {
        XSTREAM = new XStream();
        XSTREAM.autodetectAnnotations(true);
        XSTREAM.ignoreUnknownElements();
        XSTREAM.alias(WECHAT_PAYMENT_QUERY_REFUND_RESPONSE_ALIAS, WechatPaymentQueryRefundResponse.class);
    }

    public WechatPaymentQueryRefundRequest(WechatPaymentSign wechatPaymentSign) {
        super(wechatPaymentSign, QUERY_REFUND_URL, XSTREAM);
    }

}
