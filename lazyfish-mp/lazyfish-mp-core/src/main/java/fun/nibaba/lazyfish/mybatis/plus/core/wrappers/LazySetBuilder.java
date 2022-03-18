package fun.nibaba.lazyfish.mybatis.plus.core.wrappers;

import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import fun.nibaba.lazyfish.mybatis.plus.core.exceptions.LazyMybatisPlusException;
import fun.nibaba.lazyfish.mybatis.plus.core.functions.ValueFunction;
import fun.nibaba.lazyfish.mybatis.plus.core.interfaces.LazySet;
import fun.nibaba.lazyfish.mybatis.plus.core.segments.*;

/**
 * @author chenjiamin
 * @date 2022-03-14 16:35:27
 */
public class LazySetBuilder<TableModel> implements LazySet<LazySetBuilder<TableModel>, TableModel, Object> {

    private final LazyTable<TableModel> lazyTable;

    private final SetSegment setSegment;

    private final LazyParamMap paramMap;

    private LazySetBuilder(LazyTable<TableModel> lazyTable, SetSegment setSegment, LazyParamMap paramMap) {
        this.lazyTable = lazyTable;
        this.setSegment = setSegment;
        this.paramMap = paramMap;
    }

    /**
     * 构造器
     *
     * @param lazyTable    表
     * @param setSegment   set sql segment
     * @param paramMap     paramMap
     * @param <TableModel> 类型
     * @return builder
     */
    static <TableModel> LazySetBuilder<TableModel> builder(LazyTable<TableModel> lazyTable, SetSegment setSegment, LazyParamMap paramMap) {
        return new LazySetBuilder<>(lazyTable, setSegment, paramMap);
    }

    @Override
    public LazySetBuilder<TableModel> set(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Object> value) {
        if (!condition) {
            return this;
        }
        Object valueObject = value.getValue();
        ColumnCache columnCache = lazyTable.getColumnCache(column);
        String paramValue = paramMap.formatParam(this.lazyTable.getTableNameAlia(), columnCache, valueObject);
        this.setSegment.add(new CompareValueSegment(
                new ColumnSegment(
                        lazyTable.getTableNameAlia(),
                        columnCache.getColumnSelect()
                ),
                SqlKeyword.EQ,
                paramValue)
        );
        return this;
    }

    @Override
    public LazySetBuilder<TableModel> increment(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Number> value) {
        if (!condition) {
            return this;
        }
        Number valueObject = value.getValue();
        if (valueObject == null) {
            throw new LazyMybatisPlusException("自增自减的值不能为null");
        }
        ColumnCache columnCache = lazyTable.getColumnCache(column);
        ColumnSegment columnSegment = new ColumnSegment(lazyTable.getTableNameAlia(), columnCache.getColumnSelect());
        String paramValue = paramMap.formatParam(this.lazyTable.getTableNameAlia(), columnCache, valueObject);
        this.setSegment.add(new CompareValueSegment(
                columnSegment,
                SqlKeyword.EQ,
                new ColumnsIncrementSegment(columnSegment, paramValue))
        );
        return this;
    }

}
