package fun.nibaba.lazyfish.wechat.payment.properties;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * 微信支付-配置
 *
 * @author chenjiamin
 * @description
 * @date 2021/5/14 5:20 下午
 */
@Getter
public class WechatPaymentProperties {

    /**
     * appid
     */
    @NotBlank(message = "微信小程序ID不能为空")
    private final String appid;

    /**
     * 商户号
     */
    @NotBlank(message = "微信商户号不能为空")
    private final String mchId;

    /**
     * 微信商户密钥
     */
    @NotBlank(message = "微信商户密钥 不能为空")
    private final String mchSecret;

    @Builder
    public WechatPaymentProperties(String appid, String mchId, String mchSecret) {
        this.appid = appid;
        this.mchId = mchId;
        this.mchSecret = mchSecret;
    }

}
