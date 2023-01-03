package fun.nibaba.lazyfish.trans.component;

/**
 * 拆箱handler
 * 一般常用的返回方式都有包装类进行包装
 */
public interface UnboxWrapperHandler<ResultWrapper> {

    /**
     * 拆箱
     *
     * @param returnValue 值
     * @return 拆箱后的值
     */
    Object unbox(ResultWrapper returnValue);

}
