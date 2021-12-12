package fun.nibaba.lazyfish.wechat.payment.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenjiamin
 * @description 测试微信参数配置文件
 * @date 2021/5/18 6:50 下午
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wechat.payment")
public class WechatPaymentPropertiesTest {

    /**
     * appid
     */
    private String appid;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 微信商户密钥
     */
    private String mchSecret;

}
