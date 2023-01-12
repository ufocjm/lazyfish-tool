package fun.nibaba.lazyfish.trans.fields;

import fun.nibaba.lazyfish.trans.processors.TransScanProcessor;

import java.util.List;

/**
 * 具有泛型的翻译列
 */
public interface ITransGenericField extends ITransField {

    /**
     * 获取泛型的真实类型
     *
     * @return classType
     */
    default Class<?> getTrulyType(){
        return this.getTransModel().getClassType();
    }

    /**
     * 泛型对应的TransModel
     *
     * @return transModel
     */
    TransModel getTransModel();

    /**
     * 扫描<br/>
     * 本身字段不需要界定是否拥有标识等，依赖于具体类型是否有对应的标识
     *
     * @param scanProcessors 类型扫描器
     */
    @Override
    default void scan(List<TransScanProcessor<?>> scanProcessors) {
        this.getTransModel().scan(scanProcessors);
    }

    /**
     * 是否可用<br/>
     * 依赖于具体类型是否可用
     *
     * @return boolean
     */
    @Override
    default boolean valid() {
        return this.getTransModel().valid();
    }

}
