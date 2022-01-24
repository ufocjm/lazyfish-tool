package fun.nibaba.lazyfish.utils.reflect;

import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 字段包装
 *
 * @author chenjiamin
 * @date 2022/1/20 3:49 下午
 */
@Getter
public class FieldWrapper {

    private final Field field;

    private Method setMethod;

    private Method getMethod;

    public FieldWrapper(Field field) {
        this.field = field;
        if (!isPublic()) {
            Class<?> declaringClass = field.getDeclaringClass();
            String fieldName = field.getName();
            try {
                String setMethodName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                this.setMethod = declaringClass.getMethod(setMethodName, field.getType());
            } catch (NoSuchMethodException ignored) {
            }
            try {
                String getMethodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                this.getMethod = declaringClass.getMethod(getMethodName);
            } catch (NoSuchMethodException ignored) {
            }
        }
    }

    private boolean isPublic() {
        return Modifier.isPublic(field.getModifiers());
    }

    /**
     * set
     * 如果为public修饰符直接进行赋值
     * 如果没有set方法，直接通过setAccessible方式后进行赋值
     * 如果有set方法，调用set方法
     *
     * @param o     对象
     * @param value 值
     */
    public void set(Object o, Object value) {
        try {
            if (isPublic()) {
                field.set(o, value);
            } else {
                if (setMethod == null) {
                    field.setAccessible(true);
                    field.set(o, value);
                } else {
                    setMethod.invoke(o, value);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException ignored) {
        }

    }

    /**
     * get
     * 如果为public直接获取值
     * 如果没有get方法，通过setAccessible后直接获取值
     * 如果有get方法，直接调用get方法
     *
     * @param o 对象
     * @return 值
     */
    @SuppressWarnings("unchecked")
    public <T> T get(Object o) {
        try {
            if (isPublic()) {
                return (T) field.get(o);
            } else {
                if (getMethod == null) {
                    field.setAccessible(true);
                    return (T) field.get(o);
                } else {
                    return (T) getMethod.invoke(o);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

}
