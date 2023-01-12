package fun.nibaba.lazyfish.trans.fields;

import fun.nibaba.lazyfish.trans.processors.TransScanProcessor;

import java.util.List;

/**
 * 翻译元素
 */
public interface ITransElement {

    /**
     * 构建翻译处理器
     *
     * @param transHandles 处理器列表
     * @param classType    构建什么标识的处理器
     * @param returnValue  返回结果
     */
    void buildHandles(List<ITransHandle> transHandles, Class<?> classType, Object returnValue);

    /**
     * 扫描
     *
     * @param scanProcessors 类型扫描器
     */
    void scan(List<TransScanProcessor<?>> scanProcessors);

    /**
     * 是否可用
     *
     * @return boolean
     */
    boolean valid();

}
