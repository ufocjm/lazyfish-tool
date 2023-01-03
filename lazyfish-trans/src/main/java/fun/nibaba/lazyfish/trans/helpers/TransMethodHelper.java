package fun.nibaba.lazyfish.trans.helpers;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 翻译方法helper
 */
@UtilityClass
public class TransMethodHelper {

    /**
     * 根据列获取普通的get方法
     *
     * @param field 列
     * @return method
     */
    public Method generalGetter(Field field) {
        String getter = StrUtil.genGetter(field.getName());
        return ReflectUtil.getMethod(field.getDeclaringClass(), getter);
    }

    /**
     * 根据列获取普通的set方法
     *
     * @param field 列
     * @return method
     */
    public Method generalSetter(Field field) {
        String setter = StrUtil.genSetter(field.getName());
        return ReflectUtil.getMethod(field.getDeclaringClass(), setter, field.getType());
    }

}
