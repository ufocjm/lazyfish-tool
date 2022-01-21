package fun.nibaba.lazyfish.utils;

import fun.nibaba.lazyfish.utils.converters.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 转换工具
 *
 * @author chenjiamin
 * @date 2022/1/20 4:59 下午
 */
public class ConvertUtils {

    /**
     * 缓存
     */
    public final static Map<Class<?>, IConverter<?>> CONVERTER_MAP = new HashMap<>();

    /**
     * 初始化
     */
    static {
        StringConverter stringConverter = new StringConverter();
        CONVERTER_MAP.put(stringConverter.getTypeClass(), stringConverter);

        BigDecimalConverter bigDecimalConverter = new BigDecimalConverter();
        CONVERTER_MAP.put(bigDecimalConverter.getTypeClass(), bigDecimalConverter);

        BooleanConverter booleanConverter = new BooleanConverter();
        CONVERTER_MAP.put(booleanConverter.getTypeClass(), booleanConverter);

        DoubleConverter doubleConverter = new DoubleConverter();
        CONVERTER_MAP.put(doubleConverter.getTypeClass(), doubleConverter);

        FloatConverter floatConverter = new FloatConverter();
        CONVERTER_MAP.put(floatConverter.getTypeClass(), floatConverter);

        IntegerConverter integerConverter = new IntegerConverter();
        CONVERTER_MAP.put(integerConverter.getTypeClass(), integerConverter);

        LongConverter longConverter = new LongConverter();
        CONVERTER_MAP.put(longConverter.getTypeClass(), longConverter);

        ShortConverter shortConverter = new ShortConverter();
        CONVERTER_MAP.put(shortConverter.getTypeClass(), shortConverter);

        LocalDateTimeConverter localDateTimeConverter = new LocalDateTimeConverter();
        CONVERTER_MAP.put(localDateTimeConverter.getTypeClass(), localDateTimeConverter);

        LocalDateConverter localDateConverter = new LocalDateConverter();
        CONVERTER_MAP.put(localDateConverter.getTypeClass(), localDateConverter);

        LocalTimeConverter localTimeConverter = new LocalTimeConverter();
        CONVERTER_MAP.put(localTimeConverter.getTypeClass(), localTimeConverter);
    }

    /**
     * 转换
     *
     * @param value      值
     * @param targetType 目标类型
     * @return 转换结果
     */
    @SuppressWarnings("unchecked")
    public static <T> T to(Object value, Class<?> targetType) {
        IConverter converter = CONVERTER_MAP.get(value.getClass());
        if (converter == null) {
            throw new IllegalArgumentException(String.format("未知类型 %s", value.getClass().getSimpleName()));
        }
        if (targetType == String.class) {
            return (T) converter.toString(value);
        } else if (targetType == BigDecimal.class) {
            return (T) converter.toBigDecimal(value);
        } else if (targetType == double.class || targetType == Double.class) {
            return (T) converter.toDouble(value);
        } else if (targetType == float.class || targetType == Float.class) {
            return (T) converter.toFloat(value);
        } else if (targetType == int.class || targetType == Integer.class) {
            return (T) converter.toInteger(value);
        } else if (targetType == long.class || targetType == Long.class) {
            return (T) converter.toLong(value);
        } else if (targetType == short.class || targetType == Short.class) {
            return (T) converter.toShort(value);
        } else if (targetType == LocalDateTime.class) {
            return (T) converter.toLocalDateTime(value);
        } else if (targetType == LocalDate.class) {
            return (T) converter.toLocalDate(value);
        } else if (targetType == LocalTime.class) {
            return (T) converter.toLocalTime(value);
        }
        return null;
    }

}
