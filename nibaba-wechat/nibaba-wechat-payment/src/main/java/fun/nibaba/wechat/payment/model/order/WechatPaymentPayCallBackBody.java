package fun.nibaba.wechat.payment.model.order;

import com.thoughtworks.xstream.XStream;

/**
 * @author chenjiamin
 * @description 微信支付后回调实体
 * @date 2021/5/18 11:28 上午
 */
public class WechatPaymentPayCallBackBody {

    private final String xmlBody;

    private static final XStream XSTREAM;

    private static final String WECHAT_PAYMENT_PAY_CALL_BACK_BODY_ALIAS = "xml";

    static {
        XSTREAM = new XStream();
        XSTREAM.autodetectAnnotations(true);
        XSTREAM.alias(WECHAT_PAYMENT_PAY_CALL_BACK_BODY_ALIAS, WechatPaymentPayCallBackResponse.class);
    }

    public WechatPaymentPayCallBackBody(String xmlBody) {
        this.xmlBody = xmlBody;
    }

    public WechatPaymentPayCallBackResponse build() {
        return (WechatPaymentPayCallBackResponse) XSTREAM.fromXML(this.xmlBody);
    }

}
