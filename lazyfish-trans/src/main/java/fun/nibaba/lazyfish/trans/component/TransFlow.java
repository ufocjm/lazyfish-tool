package fun.nibaba.lazyfish.trans.component;

import com.google.common.collect.Lists;
import fun.nibaba.lazyfish.trans.fields.ITransHandle;
import fun.nibaba.lazyfish.trans.fields.TransModel;
import fun.nibaba.lazyfish.trans.helpers.TransModelCache;
import fun.nibaba.lazyfish.trans.helpers.TypeHelper;
import fun.nibaba.lazyfish.trans.processors.TransScanProcessor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 翻译步骤
 */
@AllArgsConstructor
public class TransFlow {

    private final List<TransScanProcessor<?>> transScanProcessors;

    /**
     * 是否扫描过
     *
     * @param returnClass returnClass
     * @return boolean
     */
    public boolean scanned(Class<?> returnClass) {
        TransModel transModel = TransModelCache.get(returnClass);
        return transModel != null;
    }

    /**
     * 扫描
     *
     * @param returnClass returnClass
     */
    public void scan(Class<?> returnClass) {
        TransModel transModel = new TransModel(returnClass);
        transModel.scan(transScanProcessors);
    }

    /**
     * 是否匹配进行翻译
     *
     * @param returnClass returnClass
     * @return boolean
     */
    public boolean match(Class<?> returnClass) {
        TransModel transModel = TransModelCache.get(returnClass);
        return transModel.valid();
    }

    /**
     * 翻译
     *
     * @param unboxReturnValue unboxReturnValue
     */
    public void trans(Object unboxReturnValue) {
        TransModel transModel = TransModelCache.get(TypeHelper.getTrulyType(unboxReturnValue));
        for (TransScanProcessor<?> transScanProcessor : transScanProcessors) {
            List<ITransHandle> transHandles = Lists.newLinkedList();
            transModel.buildHandles(transHandles, transScanProcessor.getClassType(), unboxReturnValue);
            List<Object> keys = transHandles.stream().map(ITransHandle::getKey).filter(Objects::nonNull).collect(Collectors.toList());
            Map<Object, Object> transMap = transScanProcessor.getTransMap(keys);
            for (ITransHandle transHandle : transHandles) {
                transHandle.setValue(transMap);
            }
        }
    }

}
