package fun.nibaba.lazyfish.trans.fields;

import java.util.Map;

/**
 * 翻译处理器
 */
public interface ITransHandle {

    /**
     * 获取Key
     *
     * @return key
     */
    Object getKey();

    /**
     * set value
     *
     * @param transValueMap 字典
     */
    void setValue(Map<Object, Object> transValueMap);

}
