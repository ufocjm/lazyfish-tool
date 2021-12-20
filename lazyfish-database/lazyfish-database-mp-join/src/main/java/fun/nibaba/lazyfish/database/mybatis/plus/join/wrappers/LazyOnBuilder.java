package fun.nibaba.lazyfish.database.mybatis.plus.join.wrappers;

import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import fun.nibaba.lazyfish.database.mybatis.plus.join.interfaces.LazyJoinCompare;
import fun.nibaba.lazyfish.database.mybatis.plus.join.interfaces.LazyNested;
import fun.nibaba.lazyfish.database.mybatis.plus.join.segments.*;

import java.util.function.Consumer;

/**
 * TODO
 *
 * @author chenjiamin
 * @date 2021/12/17 10:35 上午
 */
public class LazyOnBuilder<Main, Join> implements
        LazyJoinCompare<LazyOnBuilder<Main, Join>, Main, Join>,
        LazyNested<LazyOnBuilder<Main, Join>> {

    final LazyTable<Main> lazyTable;

    final LazyTable<Join> lazyJoinTable;

    final JoinWhereSegment joinOnSegment;

    private LazyOnBuilder(LazyTable<Main> lazyTable, LazyTable<Join> lazyJoinTable) {
        this.lazyTable = lazyTable;
        this.lazyJoinTable = lazyJoinTable;
        this.joinOnSegment = new JoinWhereSegment();
    }

    public static <Main, Join> LazyOnBuilder<Main, Join> builder(LazyTable<Main> lazyTable,
                                                                 LazyTable<Join> lazyJoinTable) {
        return new LazyOnBuilder<>(lazyTable, lazyJoinTable);
    }

    @Override
    public LazyOnBuilder<Main, Join> compare(boolean condition, SFunction<Main, ?> leftColumn, SqlKeyword sqlKeyword, SFunction<Join, ?> rightColumn) {
        if (!condition) {
            return this;
        }
        ColumnSegment leftColumnSegment = new ColumnSegment(lazyTable.getTableNameAlia(), lazyTable.getColumnName(leftColumn));
        ColumnSegment rightColumnSegment = new ColumnSegment(lazyJoinTable.getTableNameAlia(), lazyJoinTable.getColumnName(rightColumn));
        CompareFieldSegment compareFieldSegment = new CompareFieldSegment(leftColumnSegment, sqlKeyword, rightColumnSegment);
        joinOnSegment.add(compareFieldSegment);
        return this;
    }

    @Override
    public LazyOnBuilder<Main, Join> nested(boolean condition, Consumer<LazyOnBuilder<Main, Join>> consumer, SqlKeyword sqlKeyword) {
        if (!condition) {
            return this;
        }

        LazyOnBuilder<Main, Join> lazyOn = new LazyOnBuilder<>(this.lazyTable, this.lazyJoinTable);
        consumer.accept(lazyOn);
        this.joinOnSegment.add(new BracketSegment(lazyOn.joinOnSegment));
        return this;
    }
}
