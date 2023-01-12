package fun.nibaba.lazyfish.trans.fields;

import com.google.common.collect.Maps;
import fun.nibaba.lazyfish.trans.handles.TransListPrimitiveHandle;
import fun.nibaba.lazyfish.trans.helpers.TransMethod;
import fun.nibaba.lazyfish.trans.processors.TransScanProcessor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 泛型为基础类型的List字段
 */
public class TransListPrimitiveField implements ITransField {

    /**
     * 字段
     */
    private final Field field;

    /**
     * 翻译的方法
     */
    private final TransMethod transMethod;

    /**
     * 标识是否有效
     */
    private final Map<Class<?>, Boolean> validMap = Maps.newHashMap();


    public TransListPrimitiveField(Field field,
                                   TransMethod transMethod) {
        this.field = field;
        this.transMethod = transMethod;
    }


    @Override
    public Field getField() {
        return this.field;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void buildHandles(List<ITransHandle> transHandles, Class<?> classType, Object returnValue) {
        Boolean hasClassType = this.validMap.get(classType);
        if (hasClassType == null || !hasClassType) {
            return;
        }

        List<Object> values = (List<Object>) this.transMethod.get(returnValue);
        if (values == null) {
            return;
        }
        for (int i = 0; i < values.size(); i++) {
            transHandles.add(new TransListPrimitiveHandle(values, i));
        }
    }

    @Override
    public void scan(List<TransScanProcessor<?>> scanProcessors) {
        for (TransScanProcessor<?> scanProcessor : scanProcessors) {
            if (this.validMap.get(scanProcessor.getClassType()) == null) {
                this.validMap.put(scanProcessor.getClassType(), scanProcessor.match(this.field));
            }

        }
    }

    /**
     * 只要有一个类型有效则有效
     *
     * @return boolean
     */
    @Override
    public boolean valid() {
        Set<Map.Entry<Class<?>, Boolean>> entries = this.validMap.entrySet();
        for (Map.Entry<Class<?>, Boolean> entry : entries) {
            if (entry.getValue() != null && entry.getValue()) {
                return true;
            }
        }
        return false;
    }

}
