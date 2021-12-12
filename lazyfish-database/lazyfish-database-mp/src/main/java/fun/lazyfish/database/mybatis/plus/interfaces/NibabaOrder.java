package fun.lazyfish.database.mybatis.plus.interfaces;

import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * 排序封装
 *
 * @author chenjiamin
 * @date 2021/6/4 5:32 下午
 */
public interface NibabaOrder<Child> {

    /**
     * 排序-升序
     *
     * @param column         列名
     * @param <OrderByModel> 排序类型
     * @return 条件封装对象
     */
    default <OrderByModel> Child orderByAsc(SFunction<OrderByModel, ?> column) {
        return orderByAsc(column, null);
    }

    /**
     * 排序-升序
     *
     * @param column         列名
     * @param tableNameAlias 别名
     * @param <OrderByModel> 排序类型
     * @return 条件封装对象
     */
    default <OrderByModel> Child orderByAsc(SFunction<OrderByModel, ?> column, String tableNameAlias) {
        return orderByAsc(true, column, tableNameAlias);
    }

    /**
     * 排序-升序
     *
     * @param condition      执行条件
     * @param column         列名
     * @param <OrderByModel> 排序类型
     * @return 条件封装对象
     */
    default <OrderByModel> Child orderByAsc(boolean condition, SFunction<OrderByModel, ?> column) {
        return orderByAsc(condition, column, null);
    }

    /**
     * 排序-升序
     *
     * @param condition      执行条件
     * @param column         列名
     * @param tableNameAlias 别名
     * @param <OrderByModel> 排序类型
     * @return 条件封装对象
     */
    default <OrderByModel> Child orderByAsc(boolean condition, SFunction<OrderByModel, ?> column, String tableNameAlias) {
        return orderBy(condition, column, SqlKeyword.ASC, tableNameAlias);
    }

    /**
     * 排序-降序
     *
     * @param column         列名
     * @param <OrderByModel> 排序类型
     * @return 条件封装对象
     */
    default <OrderByModel> Child orderByDesc(SFunction<OrderByModel, ?> column) {
        return orderByDesc(column, null);
    }

    /**
     * 排序-降序
     *
     * @param column         列名
     * @param tableNameAlias 别名
     * @param <OrderByModel> 排序类型
     * @return 条件封装对象
     */
    default <OrderByModel> Child orderByDesc(SFunction<OrderByModel, ?> column, String tableNameAlias) {
        return orderByDesc(true, column, tableNameAlias);
    }

    /**
     * 排序-降序
     *
     * @param condition      执行条件
     * @param column         列名
     * @param tableNameAlias 别名
     * @param <OrderByModel> 排序类型
     * @return 条件封装对象
     */
    default <OrderByModel> Child orderByDesc(boolean condition, SFunction<OrderByModel, ?> column, String tableNameAlias) {
        return orderBy(condition, column, SqlKeyword.DESC, tableNameAlias);
    }

    /**
     * 排序
     *
     * @param condition      执行条件
     * @param column         列名
     * @param orderRule      排序规则
     * @param tableNameAlias 别名
     * @param <OrderByModel> 排序类型
     * @return 条件封装对象
     */
    <OrderByModel> Child orderBy(boolean condition, SFunction<OrderByModel, ?> column, SqlKeyword orderRule, String tableNameAlias);

}
