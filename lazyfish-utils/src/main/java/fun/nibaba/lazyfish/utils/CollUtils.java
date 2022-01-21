package fun.nibaba.lazyfish.utils;

import java.util.Collection;

/**
 * 集合工具类
 *
 * @author chenjiamin
 * @date 2022/1/21 11:02 上午
 */
public class CollUtils {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

}
