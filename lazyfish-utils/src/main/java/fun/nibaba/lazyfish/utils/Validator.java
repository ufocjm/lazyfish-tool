package fun.nibaba.lazyfish.utils;

/**
 * 校验类
 *
 * @author chenjiamin
 * @date 2022/1/20 5:45 下午
 */
public class Validator {

    /**
     * 判断字符串是否是数字
     *
     * @param value str
     * @return 结果
     */
    public static boolean isNumber(String value) {
        if (value == null || StringConstants.EMPTY.equals(value)) {
            return false;
        }
        return Patterns.NUMBER_PATTERN.matcher(value).matches();
    }

}
