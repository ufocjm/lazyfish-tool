package fun.nibaba.test.controller;

import fun.nibaba.wechat.payment.properties.WechatPaymentProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenjiamin
 * @description TODO
 * @date 2021/5/14 5:34 下午
 */
//@AllArgsConstructor
@RestController
@RequestMapping(value = "/test")
public class TestController {

    private final WechatPaymentProperties wechatProperties;

    public TestController(WechatPaymentProperties wechatProperties) {
        this.wechatProperties = wechatProperties;
    }
}
