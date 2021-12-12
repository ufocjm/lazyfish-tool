package fun.nibaba.lazyfish.wechat.payment.model.refund;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import fun.nibaba.lazyfish.wechat.payment.model.WechatPaymentResponse;
import lombok.Getter;

/**
 * 微信支付-退款回调-加密实体
 *
 * @author chenjiamin
 * @date 2021/5/18 11:28 上午
 */
@Getter
public class WechatPaymentRefundCallBackEncryptionResponse extends WechatPaymentResponse {

    /**
     * 加密信息
     */
    @XStreamAlias(value = "req_info")
    private String reqInfo;


}
