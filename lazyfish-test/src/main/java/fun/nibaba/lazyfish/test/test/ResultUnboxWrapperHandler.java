package fun.nibaba.lazyfish.test.test;

import fun.nibaba.lazyfish.test.controller.R;
import fun.nibaba.lazyfish.trans.component.UnboxWrapperHandler;
import org.springframework.stereotype.Component;

@Component
public class ResultUnboxWrapperHandler implements UnboxWrapperHandler<R<?>> {
    /**
     * 拆箱
     *
     * @param returnValue 值
     * @return 拆箱后的值
     */
    @Override
    public Object unbox(R<?> returnValue) {
        return returnValue.getData();
    }
}
