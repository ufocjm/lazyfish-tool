package fun.nibaba.lazyfish.trans.helpers;

import cn.hutool.core.util.ReflectUtil;
import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 翻译的方法
 */
@AllArgsConstructor
public class TransMethod {

    /**
     * 字段
     */
    private final Field field;

    /**
     * get方法
     */
    private final Method getterMethod;

    /**
     * set方法
     */
    private final Method setterMethod;

    /**
     * set value
     *
     * @param returnValue 对象
     * @param value       值
     */
    public void set(Object returnValue, Object value) {
        if (this.setterMethod == null) {
            ReflectUtil.setFieldValue(returnValue, this.field, value);
            return;
        }
        ReflectUtil.invoke(returnValue, this.setterMethod, value);
    }

    /**
     * get value
     *
     * @param returnValue 对象
     * @return 结果
     */
    public Object get(Object returnValue) {
        if (this.getterMethod == null) {
            return ReflectUtil.getFieldValue(returnValue, this.field);
        }
        return ReflectUtil.invoke(returnValue, this.getterMethod);
    }

}
