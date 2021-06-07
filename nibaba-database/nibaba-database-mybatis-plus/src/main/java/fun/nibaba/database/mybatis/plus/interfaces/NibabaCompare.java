package fun.nibaba.database.mybatis.plus.interfaces;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import fun.nibaba.database.mybatis.plus.functions.ValueFunction;

/**
 * 查询条件封装
 * 比较值
 *
 * @author chenjiamin
 * @date 2021/6/3 1:43 下午
 */
public interface NibabaCompare<MainTableClass, Child, Value> {

    /**
     * ignore
     */
    default Child eq(SFunction<MainTableClass, ?> column, Value value) {
        return eq(column, () -> value);
    }

    /**
     * ignore
     */
    default Child eq(SFunction<MainTableClass, ?> column, ValueFunction<Value> value) {
        return eq(true, column, value);
    }

    /**
     * equals 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return
     */
    default Child eq(boolean condition, SFunction<MainTableClass, ?> column, Value value) {
        return eq(condition, column, () -> value);
    }

    /**
     * equals 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return
     */
    Child eq(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Value> value);


    /**
     * ignore
     */
    default Child ne(SFunction<MainTableClass, ?> column, Value value) {
        return ne(column, () -> value);
    }

    /**
     * ignore
     */
    default Child ne(SFunction<MainTableClass, ?> column, ValueFunction<Value> value) {
        return ne(true, column, value);
    }

    /**
     * not equals 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return
     */
    default Child ne(boolean condition, SFunction<MainTableClass, ?> column, Value value) {
        return ne(true, column, () -> value);
    }

    /**
     * not equals  条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return
     */
    Child ne(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Value> value);

    /**
     * ignore
     */
    default Child gt(SFunction<MainTableClass, ?> column, Value value) {
        return gt(true, column, value);
    }

    /**
     * ignore
     */
    default Child gt(SFunction<MainTableClass, ?> column, ValueFunction<Value> value) {
        return gt(true, column, value);
    }

    /**
     * 大于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return
     */
    default Child gt(boolean condition, SFunction<MainTableClass, ?> column, Value value) {
        return lt(condition, column, () -> value);
    }

    /**
     * 大于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return
     */
    Child gt(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Value> value);

    /**
     * ignore
     */
    default Child ge(SFunction<MainTableClass, ?> column, Value value) {
        return ge(column, () -> value);
    }

    /**
     * ignore
     */
    default Child ge(SFunction<MainTableClass, ?> column, ValueFunction<Value> value) {
        return ge(true, column, value);
    }

    /**
     * 大于等于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return
     */
    default Child ge(boolean condition, SFunction<MainTableClass, ?> column, Value value) {
        return ge(condition, column, () -> value);
    }

    /**
     * 大于等于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return
     */
    Child ge(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Value> value);

    /**
     * ignore
     */
    default Child lt(SFunction<MainTableClass, ?> column, Value value) {
        return lt(column, () -> value);
    }

    /**
     * ignore
     */
    default Child lt(SFunction<MainTableClass, ?> column, ValueFunction<Value> value) {
        return lt(true, column, value);
    }

    /**
     * 小于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return
     */
    default Child lt(boolean condition, SFunction<MainTableClass, ?> column, Value value) {
        return lt(condition, column, () -> value);
    }

    /**
     * 小于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return
     */
    Child lt(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Value> value);

    /**
     * ignore
     */
    default Child le(SFunction<MainTableClass, ?> column, Value value) {
        return le(column, () -> value);
    }


    /**
     * ignore
     */
    default Child le(SFunction<MainTableClass, ?> column, ValueFunction<Value> value) {
        return le(true, column, value);
    }

    /**
     * 小于等于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return
     */
    default Child le(boolean condition, SFunction<MainTableClass, ?> column, Value value) {
        return le(condition, column, () -> value);
    }

    /**
     * 小于等于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return
     */
    Child le(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Value> value);

    /**
     * ignore
     */
    default Child like(SFunction<MainTableClass, ?> column, Value value) {
        return like(column, () -> value);
    }

    /**
     * ignore
     */
    default Child like(SFunction<MainTableClass, ?> column, ValueFunction<Value> value) {
        return like(true, column, value);
    }


    /**
     * 模糊 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return
     */
    default Child like(boolean condition, SFunction<MainTableClass, ?> column, Value value) {
        return like(condition, column, () -> value);
    }

    /**
     * 模糊 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return
     */
    Child like(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Value> value);

    /**
     * ignore
     */
    default Child leftLike(SFunction<MainTableClass, ?> column, Value value) {
        return leftLike(column, () -> value);
    }

    /**
     * ignore
     */
    default Child leftLike(SFunction<MainTableClass, ?> column, ValueFunction<Value> value) {
        return leftLike(true, column, value);
    }

    /**
     * 左模糊 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return
     */
    default Child leftLike(boolean condition, SFunction<MainTableClass, ?> column, Value value) {
        return leftLike(condition, column, () -> value);

    }

    /**
     * 左模糊 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return
     */
    Child leftLike(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Value> value);


    /**
     * ignore
     */
    default Child rightLike(SFunction<MainTableClass, ?> column, Value value) {
        return rightLike(column, () -> value);
    }

    /**
     * ignore
     */
    default Child rightLike(SFunction<MainTableClass, ?> column, ValueFunction<Value> value) {
        return rightLike(true, column, value);
    }

    /**
     * 右模糊 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return
     */
    default Child rightLike(boolean condition, SFunction<MainTableClass, ?> column, Value value) {
        return rightLike(condition, column, () -> value);
    }

    /**
     * 右模糊 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return
     */
    Child rightLike(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Value> value);

    /**
     * ignore
     */
    default Child notLike(SFunction<MainTableClass, ?> column, Value value) {
        return notLike(column, () -> value);
    }

    /**
     * ignore
     */
    default Child notLike(SFunction<MainTableClass, ?> column, ValueFunction<Value> value) {
        return rightLike(true, column, value);
    }

    /**
     * not like 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return
     */
    default Child notLike(boolean condition, SFunction<MainTableClass, ?> column, Value value) {
        return notLike(condition, column, () -> value);
    }

    /**
     * not like 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return
     */
    Child notLike(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Value> value);


    /**
     * ignore
     */
    default Child isNull(SFunction<MainTableClass, ?> column) {
        return isNull(true, column);
    }


    /**
     * isNull 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @return
     */
    Child isNull(boolean condition, SFunction<MainTableClass, ?> column);

    /**
     * ignore
     */
    default Child isNotNull(SFunction<MainTableClass, ?> column) {
        return isNotNull(true, column);
    }


    /**
     * isNotNull 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @return
     */
    Child isNotNull(boolean condition, SFunction<MainTableClass, ?> column);
}
