package fun.nibaba.lazyfish.mybatis.plus.core.wrappers;

import fun.nibaba.lazyfish.mybatis.plus.core.exceptions.LazyMybatisPlusException;
import fun.nibaba.lazyfish.mybatis.plus.core.interfaces.LazyUpdate;
import fun.nibaba.lazyfish.mybatis.plus.core.interfaces.LazyWhere;
import fun.nibaba.lazyfish.mybatis.plus.core.segments.SetSegment;
import fun.nibaba.lazyfish.mybatis.plus.core.segments.WhereSegment;
import fun.nibaba.lazyfish.utils.CollUtils;

import java.util.function.Consumer;

/**
 * 修改语句构建器
 *
 * @author chenjiamin
 * @date 2022-03-14 16:23:30
 */
public class LazyUpdateWrapperBuilder<TableModel> implements
        LazyUpdate<LazyUpdateWrapperBuilder<TableModel>, TableModel>,
        LazyWhere<LazyUpdateWrapperBuilder<TableModel>, TableModel> {

    private final LazyTable<TableModel> lazyTable;

    private final SetSegment setSegment;

    private final WhereSegment whereSegment;

    private LazyUpdateWrapperBuilder(LazyTable<TableModel> lazyTable) {
        this.lazyTable = lazyTable;
        setSegment = new SetSegment();
        whereSegment = new WhereSegment();
    }

    /**
     * builder
     *
     * @param lazyTable    表
     * @param <TableModel> 表类型
     * @return builder
     */
    static <TableModel> LazyUpdateWrapperBuilder<TableModel> builder(LazyTable<TableModel> lazyTable) {
        return new LazyUpdateWrapperBuilder<>(lazyTable);
    }

    @Override
    public LazyUpdateWrapperBuilder<TableModel> where(Consumer<LazyWhereBuilder<TableModel>> lazyWhere) {
        LazyWhereBuilder<TableModel> builder = LazyWhereBuilder.builder(this.lazyTable, this.whereSegment);
        lazyWhere.accept(builder);
        return this;
    }

    @Override
    public LazyUpdateWrapperBuilder<TableModel> set(Consumer<LazySetBuilder<TableModel>> lazyUpdate) {
        LazySetBuilder<TableModel> builder = LazySetBuilder.builder(this.lazyTable, setSegment);
        lazyUpdate.accept(builder);
        return this;
    }

    /**
     * 构建
     *
     * @return LazyUpdateWrapper
     */
    public LazyUpdateWrapper build() {
        if (CollUtils.isEmpty(this.setSegment)) {
            throw new LazyMybatisPlusException("set语句为空");
        }
        return new LazyUpdateWrapper(
                this.lazyTable.getTableNameAlia(),
                this.setSegment,
                this.whereSegment
        );
    }
}

