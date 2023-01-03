package fun.nibaba.lazyfish.trans.helpers;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@UtilityClass
public class TypeHelper {

    public boolean isJavaClass(Class<?> clazz) {
        return clazz != null && clazz.getClassLoader() == null;
    }

    /**
     * 是java原生类型但是不是 集合
     *
     * @param clazz
     * @return
     */
    public boolean isJavaClassNotColl(Class<?> clazz) {
        return isJavaClass(clazz) && isNotMap(clazz) && isNotIterable(clazz);
    }

    /**
     * 是用户创建的类型但是不是 集合
     *
     * @param clazz
     * @return
     */
    public boolean isNotJavaClass(Class<?> clazz) {
        return !isJavaClass(clazz) && isNotMap(clazz) && isNotIterable(clazz);
    }

    /**
     * 不是Map
     *
     * @param clazz
     * @return
     */
    public boolean isNotMap(Class<?> clazz) {
        return !isMap(clazz);
    }

    /**
     * 是Map
     *
     * @param clazz
     * @return
     */
    public boolean isMap(Class<?> clazz) {
        return Map.class.isAssignableFrom(clazz);
    }

    /**
     * 不是迭代
     *
     * @param clazz
     * @return
     */
    public boolean isNotIterable(Class<?> clazz) {
        return !isIterable(clazz);
    }


    /**
     * 是迭代
     *
     * @param clazz
     * @return
     */
    public boolean isIterable(Class<?> clazz) {
        return Iterable.class.isAssignableFrom(clazz);
    }

    /**
     * 是集合
     *
     * @param clazz
     * @return
     */
    public boolean isList(Class<?> clazz) {
        return List.class.isAssignableFrom(clazz);
    }

    /**
     * 是集合
     *
     * @param clazz
     * @return
     */
    public boolean isCollection(Class<?> clazz) {
        return Collection.class.isAssignableFrom(clazz);
    }


    /**
     * 是Set
     *
     * @param clazz
     * @return
     */
    public boolean isSet(Class<?> clazz) {
        return Set.class.isAssignableFrom(clazz);
    }

    public Method getterMethod(Field field) {
        String name = field.getName();
        String getterMethod = StrUtil.genGetter(name);
        return ReflectUtil.getMethod(field.getDeclaringClass(), getterMethod);
    }

    public Class<?> getTrulyType(Field field) {
        if (TypeHelper.isIterable(field.getType())) {
            Type typeArgument = TypeUtil.getTypeArgument(field.getGenericType());
            return (Class<?>) typeArgument;
        } else if (TypeHelper.isMap(field.getType())) {
            Type typeArgument = TypeUtil.getTypeArgument(field.getGenericType(), 1);
            return (Class<?>) typeArgument;
        } else if (!TypeHelper.isJavaClass(field.getType())) {
            return field.getType();
        }
        return field.getType();
    }

    @SuppressWarnings("unchecked")
    public Class<?> getTrulyType(Object object) {
        if (isList(object.getClass())) {
            List<Object> values = (List<Object>) object;
            if (values.size() > 0) {
                return values.get(0).getClass();
            }
        } else if (!isJavaClass(object.getClass())) {
            return object.getClass();
        } else if (isJavaClassNotColl(object.getClass())) {
            return object.getClass();
        } else if (object instanceof Field) {
            return getTrulyType((Field) object);
        }
        return null;
    }


}
