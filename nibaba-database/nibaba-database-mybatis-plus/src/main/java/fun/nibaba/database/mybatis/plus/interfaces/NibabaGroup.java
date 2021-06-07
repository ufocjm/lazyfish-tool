package fun.nibaba.database.mybatis.plus.interfaces;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * 分组封装
 *
 * @author chenjiamin
 * @date 2021/6/7 9:40 上午
 */
public interface NibabaGroup<Child> {

    /**
     * 分组
     *
     * @param column         列名
     * @param <GroupByModel> 分组类型
     * @return 条件封装对象
     */
    default <GroupByModel> Child groupBy(SFunction<GroupByModel, ?> column) {
        return groupBy(true, column);
    }

    /**
     * 分组
     *
     * @param condition      执行条件
     * @param column         列名
     * @param <GroupByModel> 排序类型
     * @return 条件封装对象
     */
    default <GroupByModel> Child groupBy(boolean condition, SFunction<GroupByModel, ?> column) {
        return groupBy(condition, column, null);
    }

    /**
     * 分组
     *
     * @param column         列名
     * @param tableNameAlias 表别名
     * @param <GroupByModel> 排序类型
     * @return 条件封装对象
     */
    default <GroupByModel> Child groupBy(SFunction<GroupByModel, ?> column, String tableNameAlias) {
        return groupBy(true, column, tableNameAlias);
    }

    /**
     * 分组
     *
     * @param condition      执行条件
     * @param column         列名
     * @param tableNameAlias 表别名
     * @param <GroupByModel> 排序类型
     * @return 条件封装对象
     */
    <GroupByModel> Child groupBy(boolean condition, SFunction<GroupByModel, ?> column, String tableNameAlias);


}
