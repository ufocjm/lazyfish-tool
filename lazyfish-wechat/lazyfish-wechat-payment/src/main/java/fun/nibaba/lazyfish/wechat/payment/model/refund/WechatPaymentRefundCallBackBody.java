package fun.nibaba.lazyfish.wechat.payment.model.refund;

import com.thoughtworks.xstream.XStream;

/**
 * 微信支付-退款回调-实体
 *
 * @author chenjiamin
 * @date 2021/5/18 11:28 上午
 */
public class WechatPaymentRefundCallBackBody {

    private final String xmlBody;

    private static final XStream XSTREAM;

    private static final String WECHAT_PAYMENT_PAY_CALL_BACK_BODY_ALIAS = "xml";

    static {
        XSTREAM = new XStream();
        XSTREAM.autodetectAnnotations(true);
        XSTREAM.alias(WECHAT_PAYMENT_PAY_CALL_BACK_BODY_ALIAS, WechatPaymentRefundCallBackEncryptionResponse.class);
    }

    public WechatPaymentRefundCallBackBody(String xmlBody) {
        this.xmlBody = xmlBody;
    }

    public WechatPaymentRefundCallBackEncryptionResponse build() {
        return (WechatPaymentRefundCallBackEncryptionResponse) XSTREAM.fromXML(this.xmlBody);
    }

}
