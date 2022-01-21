package fun.nibaba.lazyfish.utils;

import java.util.regex.Pattern;

/**
 * 正则
 *
 * @author chenjiamin
 * @date 2022/1/21 9:43 上午
 */
public interface Patterns {

    Pattern NUMBER_PATTERN = Pattern.compile("^-?\\d+(\\.\\d+)?$");

    Pattern UP_CASE = Pattern.compile("[A-Z]");

    Pattern UNDERLINE_CASE = Pattern.compile("_(\\w)");

}
