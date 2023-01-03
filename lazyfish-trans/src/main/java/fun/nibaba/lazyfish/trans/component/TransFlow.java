package fun.nibaba.lazyfish.trans.component;

import com.google.common.collect.Lists;
import fun.nibaba.lazyfish.trans.fields.ITransHandle;
import fun.nibaba.lazyfish.trans.fields.TransModel;
import fun.nibaba.lazyfish.trans.helpers.TransModelCache;
import fun.nibaba.lazyfish.trans.processors.TransProcessor;
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

    private final List<TransProcessor<?>> transProcessors;

    /**
     * 是否扫描过
     *
     * @param unboxReturnValue unboxReturnValue
     * @return boolean
     */
    public boolean scanned(Object unboxReturnValue) {
        TransModel transModel = TransModelCache.get(unboxReturnValue.getClass());
        return transModel != null;
    }

    /**
     * 扫描
     *
     * @param unboxReturnValue unboxReturnValue
     */
    public void scan(Object unboxReturnValue) {
        TransModel transModel = new TransModel(unboxReturnValue.getClass());
        transModel.scan(transProcessors);
    }

    /**
     * 是否匹配进行翻译
     *
     * @param unboxReturnValue unboxReturnValue
     * @return boolean
     */
    public boolean match(Object unboxReturnValue) {
        TransModel transModel = TransModelCache.get(unboxReturnValue.getClass());
        return transModel.valid();
    }

    /**
     * 翻译
     *
     * @param unboxReturnValue unboxReturnValue
     */
    public void trans(Object unboxReturnValue) {
        TransModel transModel = TransModelCache.get(unboxReturnValue.getClass());
        for (TransProcessor<?> transProcessor : transProcessors) {
            List<ITransHandle> transHandles = Lists.newLinkedList();
            transModel.buildHandles(transHandles, transProcessor.getClassType(), unboxReturnValue);
            List<Object> keys = transHandles.stream().map(ITransHandle::getKey).filter(Objects::nonNull).collect(Collectors.toList());
            Map<Object, Object> transMap = transProcessor.getTransMap(keys);
            for (ITransHandle transHandle : transHandles) {
                transHandle.setValue(transMap);
            }
        }
    }

}
