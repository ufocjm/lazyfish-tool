package fun.nibaba.lazyfish.mybatis.plus.core.interfaces;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import fun.nibaba.lazyfish.mybatis.plus.core.functions.ValueFunction;

/**
 * set 语句构建器接口
 *
 * @author chenjiamin
 * @date 2022-03-14 16:47:40
 */
public interface LazySet<Child extends LazySet<Child, TableModel, Value>, TableModel, Value> {

    /**
     * 设值
     *
     * @param column 列
     * @param value  值
     * @return child
     */
    default Child set(SFunction<TableModel, ?> column, Value value) {
        return set(column, () -> value);
    }

    /**
     * 设值
     *
     * @param column 列
     * @param value  值
     * @return child
     */
    default Child set(SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return set(true, column, value);
    }

    /**
     * 设值
     *
     * @param condition 条件
     * @param column    列
     * @param value     值
     * @return child
     */
    default Child set(boolean condition, SFunction<TableModel, ?> column, Value value) {
        return set(condition, column, () -> value);
    }

    /**
     * 设值
     *
     * @param condition 条件
     * @param column    列
     * @param value     值
     * @return child
     */
    Child set(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Value> value);


    /**
     * 自增
     *
     * @param column 列
     * @return child
     */
    default Child increment(SFunction<TableModel, ?> column) {
        return increment(column, () -> 1L);
    }

    /**
     * 自增
     *
     * @param column 列
     * @param value  值
     * @return child
     */
    default Child increment(SFunction<TableModel, ?> column, Number value) {
        return increment(column, () -> value);
    }

    /**
     * 自增
     *
     * @param column 列
     * @param value  值
     * @return child
     */
    default Child increment(SFunction<TableModel, ?> column, ValueFunction<Number> value) {
        return increment(true, column, value);
    }

    /**
     * 自增
     *
     * @param condition 条件
     * @param column    列
     * @param value     值
     * @return child
     */
    default Child increment(boolean condition, SFunction<TableModel, ?> column, Number value) {
        return increment(condition, column, () -> value);
    }

    /**
     * 自增
     *
     * @param condition 条件
     * @param column    列
     * @param value     值
     * @return child
     */
    Child increment(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Number> value);

    /**
     * 自减
     *
     * @param column 列
     * @return child
     */
    default Child decrement(SFunction<TableModel, ?> column) {
        return decrement(column, () -> 1L);
    }

    /**
     * 自减
     *
     * @param column 列
     * @param value  值
     * @return child
     */
    default Child decrement(SFunction<TableModel, ?> column, Number value) {
        return decrement(column, () -> value);
    }

    /**
     * 自减
     *
     * @param column 列
     * @param value  值
     * @return child
     */
    default Child decrement(SFunction<TableModel, ?> column, ValueFunction<Number> value) {
        return decrement(true, column, value);
    }

    /**
     * 自减
     *
     * @param condition 条件
     * @param column    列
     * @param value     值
     * @return child
     */
    default Child decrement(boolean condition, SFunction<TableModel, ?> column, Number value) {
        return decrement(condition, column, () -> value);
    }

    /**
     * 自减
     *
     * @param condition 条件
     * @param column    列
     * @param value     值
     * @return child
     */
    default Child decrement(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Number> value) {
        return increment(condition, column, () -> -value.getValue().longValue());
    }
}
