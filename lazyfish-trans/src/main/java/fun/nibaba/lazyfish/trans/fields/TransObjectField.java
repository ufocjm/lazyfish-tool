package fun.nibaba.lazyfish.trans.fields;

import fun.nibaba.lazyfish.trans.helpers.TypeHelper;
import fun.nibaba.lazyfish.trans.helpers.TransMethod;
import fun.nibaba.lazyfish.trans.helpers.TransModelCache;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 自定义对象字段
 */
public class TransObjectField implements ITransGenericField {

    /**
     * 字段
     */
    @Getter
    private final Field field;

    /**
     * 字段对应的TransModel
     */
    @Getter
    private final TransModel transModel;

    /**
     * 翻译的方法
     */
    private final TransMethod transMethod;

    public TransObjectField(Field field, TransMethod transMethod) {
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
    public void buildHandles(List<ITransHandle> transHandles, Class<?> classType, Object returnValue) {
        if (returnValue == null) {
            return;
        }
        Object value = this.transMethod.get(returnValue);
        if (value == null) {
            return;
        }
        this.transModel.buildHandles(transHandles, classType, value);
    }

}
