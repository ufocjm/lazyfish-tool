package fun.nibaba.lazyfish.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 实体操作类
 *
 * @author chenjiamin
 * @date 2022/1/20 4:27 下午
 */
public class BeanUtils {

    public static Map<Class<?>, Map<String, FieldWrapper>> FIELD_WRAPPER_CACHE = new HashMap<>();

    /**
     * 获取字段包装类
     *
     * @param clazz clazz
     * @return 字段包装类
     */
    public static Map<String, FieldWrapper> getFiledWrapperMap(Class<?> clazz) {
        Map<String, FieldWrapper> fieldWrapperMap = FIELD_WRAPPER_CACHE.get(clazz);
        if (fieldWrapperMap == null) {
            Field[] fields = ReflectUtils.getFields(clazz);
            fieldWrapperMap = new HashMap<>();
            for (Field field : fields) {
                fieldWrapperMap.put(field.getName(), new FieldWrapper(field));
            }
            FIELD_WRAPPER_CACHE.put(clazz, fieldWrapperMap);
        }
        return fieldWrapperMap;
    }

    /**
     * map to bean
     * 属性映射规则默认强制使用驼峰命名
     *
     * @param map   map
     * @param clazz clazz
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        return mapToBean(map, clazz, StrUtils::toCamel);
    }

    /**
     * map to bean
     *
     * @param map              map
     * @param clazz            clazz
     * @param fieldMappingRule 映射规则
     */
    @SuppressWarnings("unchecked")
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz, Function<String, String> fieldMappingRule) {
        Map<String, FieldWrapper> filedWrapperMap = getFiledWrapperMap(clazz);
        Object o = ReflectUtils.newInstance(clazz);

        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            String fieldName = fieldMappingRule.apply(key);
            FieldWrapper fieldWrapper = filedWrapperMap.get(fieldName);
            if (fieldWrapper == null) {
                continue;
            }
            Object value = map.get(key);
            Field field = fieldWrapper.getField();
            value = ConvertUtils.to(value, field.getType());
            fieldWrapper.set(o, value);
        }
        return (T) o;
    }

    /**
     * maps to bean
     *
     * @param maps  List&lt;Map&gt;
     * @param clazz clazz
     * @return List&lt;T&gt;
     */
    public static <T> List<T> mapsToBean(List<Map<String, Object>> maps, Class<T> clazz) {
        return mapsToBean(maps, clazz, StrUtils::toCamel);
    }

    /**
     * maps to bean
     *
     * @param maps             List&lt;Map&gt;
     * @param clazz            clazz
     * @param fieldMappingRule 映射规则
     * @return List&lt;T&gt;
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> mapsToBean(List<Map<String, Object>> maps, Class<T> clazz, Function<String, String> fieldMappingRule) {
        return maps
                .stream()
                .map(map -> mapToBean(map, clazz, fieldMappingRule))
                .collect(Collectors.toList());
    }

}
