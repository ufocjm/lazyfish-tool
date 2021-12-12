package fun.nibaba.lazyfish.wechat.payment.model.refund;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.ssl.SSLSocketFactoryBuilder;
import com.thoughtworks.xstream.XStream;
import fun.nibaba.lazyfish.wechat.payment.exceptions.WechatPaymentRequestException;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentParams;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentRequest;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentSign;
import fun.nibaba.lazyfish.wechat.payment.properties.WechatPaymentCertProperties;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.InputStream;
import java.security.*;

/**
 * 微信支付-创建退款-请求
 *
 * @author chenjiamin
 * @date 2021/5/21 23:26 下午
 */
@Slf4j
public class WechatPaymentCreateRefundRequest extends WechatPaymentRequest<WechatPaymentCreateRefundResponse> {

    /**
     * 创建退款接口
     */
    private static final String CREATE_REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    private static final XStream XSTREAM;

    private static final String WECHAT_PAYMENT_CREATE_REFUND_RESPONSE_ALIAS = "xml";

    private final InputStream certInputStream;

    private final char[] password;

    static {
        XSTREAM = new XStream();
        XSTREAM.autodetectAnnotations(true);
        XSTREAM.ignoreUnknownElements();
        XSTREAM.alias(WECHAT_PAYMENT_CREATE_REFUND_RESPONSE_ALIAS, WechatPaymentCreateRefundResponse.class);
    }

    public WechatPaymentCreateRefundRequest(WechatPaymentParams params, WechatPaymentCertProperties properties) {
        super(new WechatPaymentSign(params, properties), CREATE_REFUND_URL, XSTREAM);
        certInputStream = properties.getCertInputStream();
        password = properties.getMchId().toCharArray();
    }

    /**
     * 重写父类请求发起方式
     * 创建退款采用SSL连接方式
     *
     * @return
     */
    @Override
    public WechatPaymentCreateRefundResponse request() {
        try {
            KeyStore keyStore = KeyUtil.readPKCS12KeyStore(this.certInputStream, this.password);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(keyStore, password);
            SSLSocketFactory sslSocketFactory = SSLSocketFactoryBuilder.create()
                    //由于微信那边单方面改了后端协议，并且没有在文档里面和DEMO里面没有体现出来
//                    .setProtocol(SSLSocketFactoryBuilder.TLSv1)
                    .setKeyManagers(kmf.getKeyManagers())
                    //这边可以不用加，hutool 的 builder 里面默认就是new SecureRandom()
//                    .setSecureRandom(new SecureRandom())
                    .build();
            HttpRequest httpRequest = new HttpRequest(CREATE_REFUND_URL);
            httpRequest.setSSLSocketFactory(sslSocketFactory);
            String requestXmlStr = XmlUtil.mapToXmlStr(super.wechatPaymentSign.getSortMap());
            httpRequest.body(requestXmlStr);

            long startCallTime = System.currentTimeMillis();

            HttpResponse execute = httpRequest.execute();
            String resultXmlStr = execute.body();
            log.debug("[微信支付] 接口：{} 耗时：{}ms 请求响应：{}", CREATE_REFUND_URL, System.currentTimeMillis() - startCallTime, resultXmlStr);
            return super.fromXml(resultXmlStr);
        } catch (NoSuchAlgorithmException | KeyStoreException | UnrecoverableKeyException | KeyManagementException e) {
            e.printStackTrace();
        }
        throw new WechatPaymentRequestException("微信支付[退款申请]-请求异常");
    }
}
