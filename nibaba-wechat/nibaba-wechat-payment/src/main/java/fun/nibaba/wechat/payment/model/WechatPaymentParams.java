package fun.nibaba.wechat.payment.model;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author chenjiamin
 * @description 基础微信支付参数
 * @date 2021/5/15 3:05 下午
 */
@ToString
@Getter
public class WechatPaymentParams {

    /**
     * 随机字符串，长度要求在32位以内。
     */
    @NotBlank(message = "随机字符串不能为空")
    @Length(max = 32)
    private final String nonceStr;

    public WechatPaymentParams(String nonceStr) {
        this.nonceStr = nonceStr;
    }
}
