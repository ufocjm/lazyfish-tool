package fun.nibaba.lazyfish.wechat.payment.properties;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.InputStream;

/**
 * 微信支付-带有API证书配置
 *
 * @author chenjiamin
 * @date 2021/5/14 5:20 下午
 */
@Getter
public class WechatPaymentCertProperties extends WechatPaymentProperties {

    /**
     * 微信API证书
     */
    @NotNull(message = "微信API证书不能为空")
    private final InputStream certInputStream;

    public WechatPaymentCertProperties(String appid, String mchId, String mchSecret, InputStream certInputStream) {
        super(appid, mchId, mchSecret);
        this.certInputStream = certInputStream;
    }

    @ToString
    public static class WechatPaymentCertPropertiesBuilder {
        private String appid;
        private String mchId;
        private String mchSecret;
        private InputStream certInputStream;

        WechatPaymentCertPropertiesBuilder() {
        }

        public WechatPaymentCertProperties.WechatPaymentCertPropertiesBuilder appid(String appid) {
            this.appid = appid;
            return this;
        }

        public WechatPaymentCertProperties.WechatPaymentCertPropertiesBuilder mchId(String mchId) {
            this.mchId = mchId;
            return this;
        }

        public WechatPaymentCertProperties.WechatPaymentCertPropertiesBuilder mchSecret(String mchSecret) {
            this.mchSecret = mchSecret;
            return this;
        }

        public WechatPaymentCertProperties.WechatPaymentCertPropertiesBuilder certInputStream(InputStream certInputStream) {
            this.certInputStream = certInputStream;
            return this;
        }

        public WechatPaymentCertProperties build() {
            return new WechatPaymentCertProperties(this.appid, this.mchId, this.mchSecret, this.certInputStream);
        }

    }

}
