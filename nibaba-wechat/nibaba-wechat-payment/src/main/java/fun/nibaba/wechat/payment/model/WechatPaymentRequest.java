package fun.nibaba.wechat.payment.model;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpUtil;
import com.thoughtworks.xstream.XStream;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenjiamin
 * @description 微信支付请求
 * @date 2021/5/15 4:05 下午
 */
@Slf4j
@AllArgsConstructor
public class WechatPaymentRequest<Result extends WechatPaymentResponse> {

    /**
     * 微信签名实体
     */
    private final WechatPaymentSign wechatPaymentSign;

    /**
     * 请求地址 通过构造器设值
     */
    private final String requestUrl;

    /**
     * xml 转换器
     */
    private final XStream xStream;

    /**
     * 发起请求并且转换实体
     *
     * @return
     */
    public Result request() {
        wechatPaymentSign.sign();
        String xmlStr = XmlUtil.mapToXmlStr(wechatPaymentSign.getSortMap());
        long startCallTime = System.currentTimeMillis();
        String resultXmlStr = HttpUtil.post(requestUrl, xmlStr);
        log.debug("[微信支付] 接口：{} 耗时：{}ms 请求响应：{}", requestUrl, System.currentTimeMillis() - startCallTime, resultXmlStr);
        return (Result) xStream.fromXML(resultXmlStr);
    }

}
