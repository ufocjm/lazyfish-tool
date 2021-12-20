package fun.nibaba.lazyfish.database.mybatis.plus.join.wrappers;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import fun.nibaba.lazyfish.database.mybatis.plus.join.interfaces.LazyQuery;
import fun.nibaba.lazyfish.database.mybatis.plus.join.segments.SelectSegment;

/**
 * TODO
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
    public LazySelectBuilder<TableModel> select(SFunction<TableModel, ?> column) {
        selectSegment.add(() -> lazyTable.getColumnName(column));
        return this;
    }

    @SafeVarargs
    @Override
    public final LazySelectBuilder<TableModel> select(SFunction<TableModel, ?>... columns) {
        for (SFunction<TableModel, ?> column : columns) {
            selectSegment.add(() -> lazyTable.getColumnName(column));
        }
        return this;
    }

}
