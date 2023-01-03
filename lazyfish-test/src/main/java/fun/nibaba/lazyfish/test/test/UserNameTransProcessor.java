package fun.nibaba.lazyfish.test.test;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import fun.nibaba.lazyfish.trans.processors.AbstractAnnotationTransProcessor;
import fun.nibaba.lazyfish.trans.helpers.TransMethod;
import fun.nibaba.lazyfish.trans.helpers.TransMethodHelper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserNameTransProcessor extends AbstractAnnotationTransProcessor<UserName> {

    @Override
    public TransMethod getTransMethod(Field field) {
        String fieldName = field.getName();
        String getterName = StrUtil.genGetter(fieldName);
        Method getterMethod = ReflectUtil.getMethod(field.getDeclaringClass(), getterName);
        if (getterMethod == null) {
            getterMethod = TransMethodHelper.generalGetter(field);
        }

        UserName annotation = AnnotationUtil.getAnnotation(field, this.getClassType());
        // 尝试获取method
        String value = annotation.value();
        Field targetField = ReflectUtil.getField(field.getDeclaringClass(), value);
        Method setterMethod;
        if (targetField == null) {
            setterMethod = ReflectUtil.getMethod(field.getDeclaringClass(), StrUtil.genSetter(value), String.class);
        } else {
            setterMethod = TransMethodHelper.generalSetter(targetField);
        }
        return new TransMethod(field, getterMethod, setterMethod);
    }

    /**
     * 获取字典
     *
     * @param keys keys
     * @return 字典
     */
    @Override
    public Map<Object, Object> getTransMap(List<Object> keys) {
        return keys.stream().collect(Collectors.toMap(Function.identity(), o -> o + "名字"));
    }

}
