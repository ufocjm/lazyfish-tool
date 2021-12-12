package fun.nibaba.lazyfish.wechat.payment.service.order;

import cn.hutool.core.util.IdUtil;
import fun.nibaba.lazyfish.wechat.payment.PropertiesTest;
import fun.nibaba.lazyfish.wechat.payment.enums.TradeType;
import fun.nibaba.lazyfish.wechat.payment.model.order.WechatPaymentCreateOrderParams;
import fun.nibaba.lazyfish.wechat.payment.model.order.WechatPaymentQueryOrderParams;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles(value = "junit")
@SpringBootTest
class WechatPaymentOrderServiceImplTest extends PropertiesTest {

    private final String openid = "oWjAi5HPbOyQkgDXUT0lOvlFv1x8";

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
                .spbillCreateIp("127.0.0.2")
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