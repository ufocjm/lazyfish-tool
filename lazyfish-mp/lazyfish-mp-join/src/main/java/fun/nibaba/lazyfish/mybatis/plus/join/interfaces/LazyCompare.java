package fun.nibaba.lazyfish.mybatis.plus.join.interfaces;

import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import fun.nibaba.lazyfish.mybatis.plus.join.functions.ValueFunction;

/**
 * 查询条件封装
 * 比较值
 *
 * @author chenjiamin
 * @date 2021/6/3 1:43 下午
 */
public interface LazyCompare<Child extends LazyCompare<Child, TableModel, Value>, TableModel, Value> {

    /**
     * ignore
     */
    default Child eq(SFunction<TableModel, ?> column, Value value) {
        return eq(column, () -> value);
    }

    /**
     * ignore
     */
    default Child eq(SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return eq(true, column, value);
    }

    /**
     * equals 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return this
     */
    default Child eq(boolean condition, SFunction<TableModel, ?> column, Value value) {
        return eq(condition, column, () -> value);
    }

    /**
     * equals 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return this
     */
    default Child eq(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return compare(true, column, value, SqlKeyword.EQ);
    }


    /**
     * ignore
     */
    default Child ne(SFunction<TableModel, ?> column, Value value) {
        return ne(column, () -> value);
    }

    /**
     * ignore
     */
    default Child ne(SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return ne(true, column, value);
    }

    /**
     * not equals 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return this
     */
    default Child ne(boolean condition, SFunction<TableModel, ?> column, Value value) {
        return ne(true, column, () -> value);
    }

    /**
     * not equals  条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return this
     */
    default Child ne(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return compare(true, column, value, SqlKeyword.NE);
    }

    /**
     * ignore
     */
    default Child gt(SFunction<TableModel, ?> column, Value value) {
        return gt(true, column, value);
    }

    /**
     * ignore
     */
    default Child gt(SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return gt(true, column, value);
    }

    /**
     * 大于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return this
     */
    default Child gt(boolean condition, SFunction<TableModel, ?> column, Value value) {
        return lt(condition, column, () -> value);
    }

    /**
     * 大于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return this
     */
    default Child gt(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return compare(true, column, value, SqlKeyword.GT);
    }

    /**
     * ignore
     */
    default Child ge(SFunction<TableModel, ?> column, Value value) {
        return ge(column, () -> value);
    }

    /**
     * ignore
     */
    default Child ge(SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return ge(true, column, value);
    }

    /**
     * 大于等于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return this
     */
    default Child ge(boolean condition, SFunction<TableModel, ?> column, Value value) {
        return ge(condition, column, () -> value);
    }

    /**
     * 大于等于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return this
     */
    default Child ge(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return compare(true, column, value, SqlKeyword.GE);
    }

    /**
     * ignore
     */
    default Child lt(SFunction<TableModel, ?> column, Value value) {
        return lt(column, () -> value);
    }

    /**
     * ignore
     */
    default Child lt(SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return lt(true, column, value);
    }

    /**
     * 小于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return this
     */
    default Child lt(boolean condition, SFunction<TableModel, ?> column, Value value) {
        return lt(condition, column, () -> value);
    }

    /**
     * 小于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return this
     */
    default Child lt(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return compare(true, column, value, SqlKeyword.LT);
    }

    /**
     * ignore
     */
    default Child le(SFunction<TableModel, ?> column, Value value) {
        return le(column, () -> value);
    }


    /**
     * ignore
     */
    default Child le(SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return le(true, column, value);
    }

    /**
     * 小于等于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return this
     */
    default Child le(boolean condition, SFunction<TableModel, ?> column, Value value) {
        return le(condition, column, () -> value);
    }

    /**
     * 小于等于 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return this
     */
    default Child le(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return compare(true, column, value, SqlKeyword.LE);
    }

    /**
     * ignore
     */
    default Child like(SFunction<TableModel, ?> column, Value value) {
        return like(column, () -> value);
    }

    /**
     * ignore
     */
    default Child like(SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return like(true, column, value);
    }


    /**
     * 模糊 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return this
     */
    default Child like(boolean condition, SFunction<TableModel, ?> column, Value value) {
        return like(condition, column, () -> value);
    }

    /**
     * 模糊 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return this
     */
    default Child like(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return like(condition, column, value, SqlLike.DEFAULT, SqlKeyword.LIKE);
    }

    /**
     * ignore
     */
    default Child leftLike(SFunction<TableModel, ?> column, Value value) {
        return leftLike(column, () -> value);
    }

    /**
     * ignore
     */
    default Child leftLike(SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return leftLike(true, column, value);
    }

    /**
     * 左模糊 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return this
     */
    default Child leftLike(boolean condition, SFunction<TableModel, ?> column, Value value) {
        return leftLike(condition, column, () -> value);
    }

    /**
     * 左模糊 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return this
     */
    default Child leftLike(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return like(condition, column, value, SqlLike.LEFT, SqlKeyword.LIKE);
    }


    /**
     * ignore
     */
    default Child rightLike(SFunction<TableModel, ?> column, Value value) {
        return rightLike(column, () -> value);
    }

    /**
     * ignore
     */
    default Child rightLike(SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return rightLike(true, column, value);
    }

    /**
     * 右模糊 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return this
     */
    default Child rightLike(boolean condition, SFunction<TableModel, ?> column, Value value) {
        return rightLike(condition, column, () -> value);
    }

    /**
     * 右模糊 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return this
     */
    default Child rightLike(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return like(condition, column, value, SqlLike.RIGHT, SqlKeyword.LIKE);
    }

    /**
     * ignore
     */
    default Child notLike(SFunction<TableModel, ?> column, Value value) {
        return notLike(column, () -> value);
    }

    /**
     * ignore
     */
    default Child notLike(SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return notLike(true, column, value);
    }

    /**
     * not like 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值
     * @return this
     */
    default Child notLike(boolean condition, SFunction<TableModel, ?> column, Value value) {
        return notLike(condition, column, () -> value);
    }

    /**
     * not like 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @param value     值函数
     * @return this
     */
    default Child notLike(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Value> value) {
        return like(condition, column, value, SqlLike.DEFAULT, SqlKeyword.NOT_LIKE);
    }

    /**
     * compare
     *
     * @param condition  条件
     * @param column     列
     * @param value      值
     * @param sqlKeyword sql连接标识
     * @return this
     */
    Child compare(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Value> value, SqlKeyword sqlKeyword);

    /**
     * like
     *
     * @param condition  条件
     * @param column     列
     * @param value      值
     * @param sqlLike    like标识
     * @param sqlKeyword sql连接标识
     * @return this
     */
    Child like(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Value> value, SqlLike sqlLike, SqlKeyword sqlKeyword);


    /**
     * ignore
     */
    default Child isNull(SFunction<TableModel, ?> column) {
        return isNull(true, column);
    }


    /**
     * isNull 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @return this
     */
    default Child isNull(boolean condition, SFunction<TableModel, ?> column) {
        return nullable(condition, column, SqlKeyword.IS_NULL);
    }

    /**
     * ignore
     */
    default Child isNotNull(SFunction<TableModel, ?> column) {
        return isNotNull(true, column);
    }


    /**
     * isNotNull 条件
     *
     * @param condition 执行条件
     * @param column    字段
     * @return this
     */
    default Child isNotNull(boolean condition, SFunction<TableModel, ?> column) {
        return nullable(condition, column, SqlKeyword.IS_NOT_NULL);
    }

    /**
     * 是否null
     *
     * @param condition  条件
     * @param column     列
     * @param sqlKeyword sql连接标识
     * @return this
     */
    Child nullable(boolean condition, SFunction<TableModel, ?> column, SqlKeyword sqlKeyword);

}
