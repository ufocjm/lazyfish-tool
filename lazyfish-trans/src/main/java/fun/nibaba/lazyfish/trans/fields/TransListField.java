package fun.nibaba.lazyfish.trans.fields;

import fun.nibaba.lazyfish.trans.helpers.TypeHelper;
import fun.nibaba.lazyfish.trans.helpers.TransMethod;
import fun.nibaba.lazyfish.trans.helpers.TransModelCache;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.List;

/**
 * List字段
 */
public class TransListField implements ITransGenericField {

    /**
     * 泛型具体的TransModel
     */
    @Getter
    private final TransModel transModel;

    /**
     * 字段
     */
    private final Field field;

    /**
     * 翻译的方法
     */
    private final TransMethod transMethod;

    public TransListField(Field field,
                          TransMethod transMethod) {
        this.field = field;
        Class<?> trulyType = TypeHelper.getTrulyType(field);
        if (TransModelCache.containsKey(trulyType)) {
            this.transModel = TransModelCache.get(trulyType);
        } else {
            this.transModel = new TransModel(trulyType);
        }
        this.transMethod = transMethod;
    }


    @Override
    public Field getField() {
        return this.field;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void buildHandles(List<ITransHandle> transHandles, Class<?> classType, Object returnValue) {
        List<Object> values = (List<Object>) this.transMethod.get(returnValue);
        if (values == null) {
            return;
        }
        for (Object value : values) {
            this.getTransModel().buildHandles(transHandles, classType, value);
        }
    }

}
