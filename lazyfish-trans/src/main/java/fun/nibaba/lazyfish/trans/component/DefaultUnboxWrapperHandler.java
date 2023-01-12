package fun.nibaba.lazyfish.trans.component;

/**
 * 默认拆箱工具
 */
public class DefaultUnboxWrapperHandler implements UnboxWrapperHandler<Object> {

    @Override
    public Object unbox(Object returnValue) {
        return returnValue;
    }

}
