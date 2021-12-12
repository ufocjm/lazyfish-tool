package fun.nibaba.lazyfish.wechat.payment.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import fun.nibaba.lazyfish.wechat.payment.properties.WechatPaymentProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 微信支付-签名
 *
 * @author chenjiamin
 * @date 2021/5/14 6:14 下午
 */
@Slf4j
public class WechatPaymentSign {

    /**
     * 加密参数实体
     */
    private final WechatPaymentParams params;

    /**
     * 微信商户配置
     */
    private final WechatPaymentProperties properties;

    @Getter
    private String sign;

    @Getter
    private Map<String, Object> sortMap;

    /**
     * appid key
     */
    private static final String APP_ID_KEY = "appid";

    /**
     * mch_id key
     */
    private static final String MCH_ID_KEY = "mch_id";

    /**
     * 签名字段的key
     */
    private static final String SIGN_KEY = "sign";

    /**
     * 商户密钥的key
     */
    private static final String MCH_SECRET_KEY = "key";

    public WechatPaymentSign(WechatPaymentParams params, WechatPaymentProperties properties) {
        if (params == null) {
            throw new NullPointerException("加密参数实体不能为空");
        }
        if (properties == null) {
            throw new NullPointerException("微信商户配置不能为空");
        }
        this.params = params;
        this.properties = properties;
    }

    /**
     * 执行签名
     *
     * @return
     */
    public WechatPaymentSign sign() {
        return this.sign(true);
    }

    /**
     * 执行签名
     *
     * @param toUnderlineCase 是否需要将 params 转为下划线的Map
     * @return
     */
    public WechatPaymentSign sign(boolean toUnderlineCase) {
        sortMap = BeanUtil.beanToMap(params, new TreeMap<>(), toUnderlineCase, true);

        sortMap.put(APP_ID_KEY, properties.getAppid());
        sortMap.put(MCH_ID_KEY, properties.getMchId());

        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, Object>> entries = sortMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String paramKey = entry.getKey();
            String paramValue = entry.getValue().toString();
            if (StrUtil.isNotBlank(paramValue) && !SIGN_KEY.equals(paramKey) && !MCH_SECRET_KEY.equals(paramKey)) {
                sb.append(paramKey).append("=").append(paramValue).append("&");
            }
        }

        sb.append(MCH_SECRET_KEY).append("=").append(properties.getMchSecret());
        log.debug("签名参数:[{}]", sb.toString());

        this.sign = SecureUtil.md5(sb.toString()).toUpperCase();
        log.debug("签名字符串:[{}]", this.sign);

        sortMap.put(SIGN_KEY, sign);
        return this;
    }


}
