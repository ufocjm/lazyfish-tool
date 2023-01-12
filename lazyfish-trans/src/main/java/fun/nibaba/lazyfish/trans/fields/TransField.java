package fun.nibaba.lazyfish.trans.fields;

import com.google.common.collect.Maps;
import fun.nibaba.lazyfish.trans.handles.TransHandle;
import fun.nibaba.lazyfish.trans.helpers.TransMethod;
import fun.nibaba.lazyfish.trans.processors.TransScanProcessor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class TransField implements ITransField {

    protected final Field field;

    protected final Map<Class<?>, TransMethod> obtainMethodMap = Maps.newHashMap();

    public TransField(Field field) {
        this.field = field;
    }

    @Override
    public Field getField() {
        return this.field;
    }

    @Override
    public void scan(List<TransScanProcessor<?>> scanProcessors) {
        for (TransScanProcessor<?> scanProcessor : scanProcessors) {
            if (scanProcessor.match(this.field)) {
                obtainMethodMap.computeIfAbsent(scanProcessor.getClassType(), k -> scanProcessor.getTransMethod(this.field));
            }
        }
    }

    @Override
    public void buildHandles(List<ITransHandle> transHandles, Class<?> classType, Object returnValue) {
        if (returnValue == null) {
            return;
        }
        TransMethod transMethod = this.obtainMethodMap.get(classType);
        Object key = transMethod.get(returnValue);
        if (key != null) {
            transHandles.add(new TransHandle(transMethod, returnValue, key));
        }
    }

    @Override
    public boolean valid() {
        return !this.obtainMethodMap.isEmpty();
    }

}
