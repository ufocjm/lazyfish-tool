package fun.nibaba.lazyfish.trans.handles;

import fun.nibaba.lazyfish.trans.fields.ITransHandle;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * 泛型为原始类型的List
 */
@Getter
@AllArgsConstructor
public class TransListPrimitiveHandle implements ITransHandle {

    /**
     * List对象
     */
    private final List<Object> returnValue;

    /**
     * 当前的值在List中的下标
     */
    private final int index;

    public void setValue(Map<Object, Object> transValueMap) {
        List<Object> returnValues = this.getReturnValue();
        Object key = returnValues.get(this.getIndex());
        if (key == null) {
            return;
        }
        Object value = transValueMap.get(key);
        if (value == null) {
            return;
        }
        returnValues.set(this.getIndex(), value);
    }

    @Override
    public Object getKey() {
        return this.index < this.returnValue.size() ? this.returnValue.get(this.index) : null;
    }

}
