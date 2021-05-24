package fun.nibaba.wechat.payment.model;

import lombok.ToString;

/**
 * 微信支付-返回结果
 *
 * @author chenjiamin
 * @date 2021/5/14 11:49 下午
 */
@ToString
public class WechatPaymentResult {

    /**
     * SUCCESS/FAIL
     */
    private final String returnCode;

    /**
     * 返回信息，如非空，为错误原因
     * 签名失败
     * 参数格式校验错误
     */
    private final String returnMsg;

    /**
     * 调用接口提交的公众账号ID
     */
    private final String appid;

    /**
     * 调用接口提交的商户号
     */
    private final String mchId;

    /**
     * 微信返回的随机字符串
     */
    private final String nonceStr;


    /**
     * 微信返回的签名值，详见签名算法
     */
    private final String sign;

    public WechatPaymentResult(String returnCode, String returnMsg, String appid, String mchId, String nonceStr, String sign) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.appid = appid;
        this.mchId = mchId;
        this.nonceStr = nonceStr;
        this.sign = sign;
    }
}
