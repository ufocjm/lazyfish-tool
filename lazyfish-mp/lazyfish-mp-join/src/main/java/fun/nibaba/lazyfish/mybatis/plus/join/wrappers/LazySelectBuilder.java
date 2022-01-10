package fun.nibaba.lazyfish.mybatis.plus.join.wrappers;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import fun.nibaba.lazyfish.mybatis.plus.join.interfaces.LazyQuery;
import fun.nibaba.lazyfish.mybatis.plus.join.segments.ColumnAsSegment;
import fun.nibaba.lazyfish.mybatis.plus.join.segments.ColumnSegment;
import fun.nibaba.lazyfish.mybatis.plus.join.segments.SelectSegment;

/**
 * select 构建器
 *
 * @author chenjiamin
 * @date 2021/12/14 2:34 下午
 */
public class LazySelectBuilder<TableModel> implements LazyQuery<LazySelectBuilder<TableModel>, TableModel> {

    final LazyTable<TableModel> lazyTable;

    final SelectSegment selectSegment;

    private LazySelectBuilder(LazyTable<TableModel> lazyTable) {
        this.lazyTable = lazyTable;
        selectSegment = new SelectSegment(this.lazyTable.getTableNameAlia());
    }

    public static <TableModel> LazySelectBuilder<TableModel> builder(LazyTable<TableModel> lazyTable) {
        return new LazySelectBuilder<>(lazyTable);
    }


    @Override
    public LazySelectBuilder<TableModel> select(SFunction<TableModel, ?> column, String aliasName) {
        ColumnSegment columnSegment = new ColumnSegment(lazyTable.getTableNameAlia(), lazyTable.getColumnName(column));
        selectSegment.add(new ColumnAsSegment(columnSegment, aliasName));
        return this;
    }

    @SafeVarargs
    @Override
    public final LazySelectBuilder<TableModel> select(SFunction<TableModel, ?>... columns) {
        for (SFunction<TableModel, ?> column : columns) {
            this.select(column);
        }
        return this;
    }

}
