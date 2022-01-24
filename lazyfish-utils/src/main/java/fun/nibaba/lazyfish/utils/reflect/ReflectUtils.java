package fun.nibaba.lazyfish.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 反射类
 * 主要用于反射类里面的属性以及方法等
 *
 * @author chenjiamin
 * @date 2022/1/20 3:17 下午
 */
public class ReflectUtils {

    public static Map<Class<?>, Field[]> FIELD_CACHE = new HashMap<>();

    /**
     * 获取字段数组
     *
     * @param clazz 类型
     * @return 字段数组
     */
    public static Field[] getFields(Class<?> clazz) {
        Field[] fields = FIELD_CACHE.get(clazz);
        if (fields != null) {
            return fields;
        }

        Class<?> currentClass = clazz;
        List<Field> fieldList = new ArrayList<>();
        while (currentClass != null) {
            Field[] declaredFields = currentClass.getDeclaredFields();
            fieldList.addAll(Arrays.asList(declaredFields));
            currentClass = currentClass.getSuperclass();
        }

        return fieldList.toArray(new Field[0]);
    }

    /**
     * new 对象
     *
     * @param clazz 类型
     * @return 实例对象
     */
    public static Object newInstance(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * 获取第一个泛型
     *
     * @param clazz 类型
     * @return 泛型类型
     */
    public static Class<?> getGenericType(Class<?> clazz) {
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        return (Class<?>) actualTypeArguments[0];
    }

}
