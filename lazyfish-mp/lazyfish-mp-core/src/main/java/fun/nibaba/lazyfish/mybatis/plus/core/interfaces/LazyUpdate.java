package fun.nibaba.lazyfish.mybatis.plus.core.interfaces;

import fun.nibaba.lazyfish.mybatis.plus.core.wrappers.LazySetBuilder;

import java.util.function.Consumer;

/**
 * @author chenjiamin
 * @date 2022-03-14 16:33:52
 */
public interface LazyUpdate<Child extends LazyUpdate<Child, TableModel>, TableModel> {

    /**
     * 赋值构建器
     *
     * @param lazyUpdate consumer
     * @return 实现子类
     */
    Child set(Consumer<LazySetBuilder<TableModel>> lazyUpdate);

}
