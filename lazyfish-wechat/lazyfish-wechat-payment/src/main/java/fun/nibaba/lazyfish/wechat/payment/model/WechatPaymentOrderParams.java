package fun.nibaba.lazyfish.wechat.payment.model;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * 微信支付-订单-基础参数
 * 微信支付所有的都是围绕订单号来的
 *
 * @author chenjiamin
 * @date 2021/5/14 6:30 下午
 */
@ToString
@Getter
public class WechatPaymentOrderParams extends WechatPaymentParams {

    /**
     * 为了方便业务操作这里统一采用订单号，查询订单，订单结果，订单退款，退款结果 都使用这个
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
     * <p>
     * 因为只有在创建订单的时候才是必传，其他请求的时候可以采用微信的订单号或者退款单号等等方式来请求，这里字段校验下沉到各个子类
     */
//    @NotBlank(message = "订单号不能为空")
    @Length(max = 32)
    private final String outTradeNo;

    public WechatPaymentOrderParams(String nonceStr, String outTradeNo) {
        super(nonceStr);
        this.outTradeNo = outTradeNo;
    }
}
