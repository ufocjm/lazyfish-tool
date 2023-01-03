package fun.nibaba.lazyfish.trans.component;

public class DefaultUnboxWrapperHandler implements UnboxWrapperHandler{

    @Override
    public Object unbox(Object returnValue) {
        return returnValue;
    }

}
