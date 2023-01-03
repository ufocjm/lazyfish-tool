package fun.nibaba.lazyfish.trans.handles;

import fun.nibaba.lazyfish.trans.fields.ITransHandle;
import fun.nibaba.lazyfish.trans.helpers.TransMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * 最终翻译的执行
 * 这里已经拿到对象以及对象的key，只要从字典中获取出来值后调用transMethod就可以了
 */
@Getter
@AllArgsConstructor
public class TransHandle implements ITransHandle {

    /**
     * 翻译的方法
     */
    private final TransMethod transMethod;

    /**
     * 对象
     */
    private final Object returnValue;

    /**
     * key
     */
    private final Object key;

    public void setValue(Map<Object, Object> transValueMap) {
        Object value = transValueMap.get(this.key);
        if (value != null) {
            this.transMethod.set(this.returnValue, value);
        }
    }

}
