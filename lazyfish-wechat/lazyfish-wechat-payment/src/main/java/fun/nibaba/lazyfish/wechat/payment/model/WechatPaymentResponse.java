package fun.nibaba.lazyfish.wechat.payment.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import fun.nibaba.lazyfish.wechat.payment.enums.ResultCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author chenjiamin
 * @description 微信支付 返回结果
 * @date 2021/5/14 11:49 下午
 */
@Getter
@ToString
public class WechatPaymentResponse {

    /**
     * SUCCESS/FAIL
     */
    @XStreamAlias(value = "return_code")
    protected String returnCode;

    /**
     * 返回信息，如非空，为错误原因
     * 签名失败
     * 参数格式校验错误
     */
    @XStreamAlias(value = "return_msg")
    protected String returnMsg;

    /**
     * 调用接口提交的公众账号ID
     */
    @XStreamAlias(value = "appid")
    protected String appid;

    /**
     * 调用接口提交的商户号
     */
    @XStreamAlias(value = "mch_id")
    protected String mchId;

    /**
     * 微信返回的随机字符串
     */
    @XStreamAlias(value = "nonce_str")
    protected String nonceStr;


    /**
     * 微信返回的签名值，详见签名算法
     */
    @XStreamAlias(value = "sign")
    protected String sign;


    /**
     * 请求成功
     *
     * @return
     */
    public boolean requestSuccess() {
        return ResultCode.SUCCESS.toString().equals(this.returnCode);
    }

}
