package fun.nibaba.lazyfish.wechat.payment;

import fun.nibaba.lazyfish.wechat.payment.properties.WechatPaymentProperties;
import fun.nibaba.lazyfish.wechat.payment.properties.WechatPaymentPropertiesTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenjiamin
 * @description 配置测试
 * @date 2021/5/18 7:10 下午
 */
public class PropertiesTest {

    @Autowired
    private WechatPaymentPropertiesTest wechatPaymentPropertiesTest;


    protected WechatPaymentProperties properties;

    @BeforeEach
    public void createTestOrder() {
        properties = WechatPaymentProperties.builder()
                .appid(wechatPaymentPropertiesTest.getAppid())
                .mchId(wechatPaymentPropertiesTest.getMchId())
                .mchSecret(wechatPaymentPropertiesTest.getMchSecret())
                .build();
    }


}
