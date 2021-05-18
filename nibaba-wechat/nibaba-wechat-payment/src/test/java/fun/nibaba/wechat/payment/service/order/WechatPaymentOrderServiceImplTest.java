package fun.nibaba.wechat.payment.service.order;

import cn.hutool.core.util.IdUtil;
import fun.nibaba.wechat.payment.SpringBootApplicationTest;
import fun.nibaba.wechat.payment.enums.TradeType;
import fun.nibaba.wechat.payment.model.order.WechatPaymentCreateOrderParams;
import fun.nibaba.wechat.payment.model.order.WechatPaymentQueryOrderParams;
import fun.nibaba.wechat.payment.properties.WechatPaymentProperties;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = SpringBootApplicationTest.class)
class WechatPaymentOrderServiceImplTest {

    private final String openid = "";

    private final WechatPaymentProperties properties = WechatPaymentProperties.builder()

            .build();

    private final String outTradeNo = "test_order_nibaba";
    //    private final String outTradeNo = "OR20210509003706218";
//    private final String outTradeNo = "OR20210509004132414";
//    private final String outTradeNo = "OR20210509004510220";
//    private final String outTradeNo = "1";
//    private final String outTradeNo = "OR20210509004406259";
    private final List<String> outTradeNos = Lists.newArrayList("OR20210509003706218", "OR20210509004132414", "OR20210509004510220", "1", "OR20210509004406259");


    @Autowired
    private WechatPaymentOrderService wechatPaymentOrderService;

    @Test
    void createOrder() {
        wechatPaymentOrderService.createOrder(WechatPaymentCreateOrderParams.builder()
                .body("测试商品")
                .totalFee(1)
                .spbillCreateIp("127.0.0.1")
                .notifyUrl("https://www.baidu.com")
                .tradeType(TradeType.JSAPI.getValue())
                .openid(openid)
                .outTradeNo(outTradeNo)
                .nonceStr(IdUtil.simpleUUID())
                .build(), properties);

    }

    @Test
    void queryOrder() {
        for (String tradeNo : outTradeNos) {
            wechatPaymentOrderService.queryOrder(WechatPaymentQueryOrderParams.builder()
                    .outTradeNo(tradeNo)
                    .nonceStr(IdUtil.simpleUUID())
                    .build(), properties);
        }
    }

    @Test
    void callBack() {
    }
}