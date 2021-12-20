package fun.nibaba.lazyfish.database.mybatis.plus.join.interfaces;

import fun.nibaba.lazyfish.database.mybatis.plus.join.wrappers.LazyOrderBuilder;

import java.util.function.Consumer;

/**
 * 排序接口
 *
 * @author chenjiamin
 * @date 2021/12/17 10:14 上午
 */
public interface LazyOrder<Self extends LazyOrder<Self, TableModel>, TableModel> {

    /**
     * 排序构建器
     *
     * @param lazyOrder consumer
     * @return 实现子类
     */
    Self order(Consumer<LazyOrderBuilder<TableModel>> lazyOrder);

}
