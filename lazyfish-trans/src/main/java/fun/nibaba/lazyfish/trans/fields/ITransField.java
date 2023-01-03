package fun.nibaba.lazyfish.trans.fields;

import java.lang.reflect.Field;

/**
 * 翻译列
 */
public interface ITransField extends ITransElement {

    /**
     * 获取field
     *
     * @return field
     */
    Field getField();

}
