package fun.nibaba.lazyfish.database.mybatis.plus.interfaces;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * 查询条件封装
 * join 后面的条件
 *
 * @author chenjiamin
 * @date 2021/6/4 9:59 上午
 */
public interface LazyJoinCompare<MainTableClass, Child> {

    /**
     * ignore
     */
    default <RightColumnModel> Child joinEq(SFunction<MainTableClass, ?> leftColumn, SFunction<RightColumnModel, ?> rightColumn) {
        return joinEq(true, leftColumn, rightColumn);
    }

    /**
     * join equals 条件
     *
     * @param condition          执行条件
     * @param leftColumn         左边列
     * @param rightColumn        右边列
     * @param <RightColumnModel> 右边列类型
     * @return
     */
    default <RightColumnModel> Child joinEq(boolean condition, SFunction<MainTableClass, ?> leftColumn, SFunction<RightColumnModel, ?> rightColumn) {
        return joinEq(condition, leftColumn, rightColumn, null);
    }

    /**
     * join equals 条件
     *
     * @param leftColumn         左边列
     * @param rightColumn        右边列
     * @param tableNameAlias     表别名
     * @param <RightColumnModel> 右边列类型
     * @return
     */
    default <RightColumnModel> Child joinEq(SFunction<MainTableClass, ?> leftColumn, SFunction<RightColumnModel, ?> rightColumn, String tableNameAlias) {
        return joinEq(true, leftColumn, rightColumn, tableNameAlias);
    }

    /**
     * join equals 条件
     *
     * @param condition          执行条件
     * @param leftColumn         左边列
     * @param rightColumn        右边列
     * @param tableNameAlias     指定表别名
     * @param <RightColumnModel> 右边列类型
     * @return
     */
    <RightColumnModel> Child joinEq(boolean condition, SFunction<MainTableClass, ?> leftColumn, SFunction<RightColumnModel, ?> rightColumn, String tableNameAlias);


    /**
     * ignore
     */
    default <RightColumnModel> Child joinNe(SFunction<MainTableClass, ?> leftColumn, SFunction<RightColumnModel, ?> rightColumn) {
        return joinNe(true, leftColumn, rightColumn);
    }

    /**
     * join not equals 条件
     *
     * @param condition          执行条件
     * @param leftColumn         左边列
     * @param rightColumn        右边列
     * @param <RightColumnModel> 右边列类型
     * @return
     */
    default <RightColumnModel> Child joinNe(boolean condition, SFunction<MainTableClass, ?> leftColumn, SFunction<RightColumnModel, ?> rightColumn) {
        return joinNe(condition, leftColumn, rightColumn, null);
    }

    /**
     * join not equals 条件
     *
     * @param leftColumn         左边列
     * @param rightColumn        右边列
     * @param tableNameAlias     表别名
     * @param <RightColumnModel> 右边列类型
     * @return
     */
    default <RightColumnModel> Child joinNe(SFunction<MainTableClass, ?> leftColumn, SFunction<RightColumnModel, ?> rightColumn, String tableNameAlias) {
        return joinNe(true, leftColumn, rightColumn, tableNameAlias);
    }

    /**
     * join not equals 条件
     *
     * @param condition          执行条件
     * @param leftColumn         左边列
     * @param rightColumn        右边列
     * @param tableNameAlias     指定表别名
     * @param <RightColumnModel> 右边列类型
     * @return
     */
    <RightColumnModel> Child joinNe(boolean condition, SFunction<MainTableClass, ?> leftColumn, SFunction<RightColumnModel, ?> rightColumn, String tableNameAlias);
}
