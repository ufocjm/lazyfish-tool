package fun.nibaba.database.mybatis.plus.interfaces;

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
     * 排序-生序
     *
     * @param column         列名
     * @param <OrderByModel> 排序类型
     * @return 条件封装对象
     */
    default <OrderByModel> Child orderByAsc(SFunction<OrderByModel, ?> column) {
        return orderBy(column, SqlKeyword.ASC);
    }

    /**
     * 排序-倒序
     *
     * @param column         列名
     * @param <OrderByModel> 排序类型
     * @return 条件封装对象
     */
    default <OrderByModel> Child orderByDesc(SFunction<OrderByModel, ?> column) {
        return orderBy(column, SqlKeyword.DESC);
    }

    /**
     * 排序
     *
     * @param column         列名
     * @param orderRule     排序规则
     * @param <OrderByModel> 排序类型
     * @return 条件封装对象
     */
    <OrderByModel> Child orderBy(SFunction<OrderByModel, ?> column, SqlKeyword orderRule);

}
