package fun.nibaba.lazyfish.wechat.payment.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;


/**
 * 微信AES工具
 *
 * @author chenjiamin
 */
@Slf4j
@UtilityClass
public class WechatAesUtil {

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";

    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_MODE_AES_CBC_PADDING = "AES/CBC/PKCS7Padding";

    private static final String ALGORITHM_MODE_AES_ECB_PADDING = "AES/ECB/PKCS7Padding";

    public static boolean initialized = false;

    static {
        try {
            Security.addProvider(new BouncyCastleProvider());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * AES解密
     *
     * @param content 密文
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchProviderException
     */
    public byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) throws InvalidAlgorithmParameterException {
        initialize();
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_AES_CBC_PADDING);
            Key sKeySpec = new SecretKeySpec(keyByte, ALGORITHM);
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException e) {
            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public static void initialize() {
        if (initialized) {
            return;
        }
        synchronized (WechatAesUtil.class) {
            Security.addProvider(new BouncyCastleProvider());
            initialized = true;
        }
    }

    /**
     * 生成iv
     *
     * @param iv
     * @return
     * @throws Exception
     */
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }

    /**
     * AES解密
     *
     * @param encryptionData 密文
     * @param encryptionKey  密钥
     * @return
     */
    public String decrypt(String encryptionData, String encryptionKey) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_AES_ECB_PADDING, "BC");
            SecretKeySpec secretKeySpec = new SecretKeySpec(SecureUtil.md5(encryptionKey).getBytes(), ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return new String(cipher.doFinal(Base64.decode(encryptionData)));

        } catch (NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException | NoSuchProviderException e) {
            e.printStackTrace();
        }
        log.error("解密失败:encryptionData[{}],encryptionKey[{}]", encryptionData, encryptionKey);
        throw new RuntimeException("解密失败[" + encryptionData + "]");
    }

}
