package fun.nibaba.lazyfish.utils;

import java.util.regex.Matcher;

/**
 * 字符串工具
 *
 * @author chenjiamin
 * @date 2022/1/20 5:53 下午
 */
public class StrUtils {

    /**
     * 是否为空
     *
     * @param value str
     * @return 结果
     */
    public static boolean isEmpty(String value) {
        return value == null || StringConstants.EMPTY.equals(value);
    }

    /**
     * 是否不为空
     *
     * @param value str
     * @return 结果
     */
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    /**
     * 是否为空
     * 必须得所有都为空才算是空
     *
     * @param value str
     * @return 结果
     */
    public static boolean isBlank(String value) {
        if (isEmpty(value)) {
            return true;
        }
        int length = value.length();
        for (int i = 0; i < length; ++i) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否不为空
     *
     * @param value str
     * @return 结果
     */
    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    /**
     * 下划线转驼峰
     *
     * @param value str
     * @return 结果
     */
    public static String toCamel(String value) {
        if (isBlank(value)) {
            return value;
        }
        value = value.toLowerCase();
        Matcher matcher = Patterns.UNDERLINE_CASE.matcher(value);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param value str
     * @return 结果
     */
    public static String toUnderLine(String value) {
        if (isBlank(value)) {
            return value;
        }
        Matcher matcher = Patterns.UP_CASE.matcher(value);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, StringConstants.UNDER_LINE + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
