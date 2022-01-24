package fun.nibaba.lazyfish.utils.converters;

import fun.nibaba.lazyfish.utils.reflect.ReflectUtils;

/**
 * 值转换器 抽象实现类
 *
 * @author chenjiamin
 * @date 2022/1/20 5:17 下午
 */
public abstract class AbstractConverter<ValueType> implements IConverter<ValueType> {

    @SuppressWarnings("unchecked")
    @Override
    public Class<ValueType> getTypeClass() {
        return (Class<ValueType>) ReflectUtils.getGenericType(this.getClass());
    }

    @Override
    public String toString(ValueType value) {
        return value == null ? null : value.toString();
    }
}
