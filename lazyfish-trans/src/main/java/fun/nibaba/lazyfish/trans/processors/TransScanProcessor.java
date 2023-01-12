package fun.nibaba.lazyfish.trans.processors;

import fun.nibaba.lazyfish.trans.helpers.TransMethod;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 扫描处理器
 *
 * @param <T>
 */
public interface TransScanProcessor<T> {

    /**
     * 标识的classType
     *
     * @return classType
     */
    Class<T> getClassType();

    /**
     * 是否匹配
     *
     * @param field 列
     * @return 结果
     */
    boolean match(Field field);

    /**
     * 翻译方法
     *
     * @param field 列
     * @return 结果
     */
    TransMethod getTransMethod(Field field);

    /**
     * 获取字典
     *
     * @param keys keys
     * @return 字典
     */
    Map<Object, Object> getTransMap(List<Object> keys);

}
