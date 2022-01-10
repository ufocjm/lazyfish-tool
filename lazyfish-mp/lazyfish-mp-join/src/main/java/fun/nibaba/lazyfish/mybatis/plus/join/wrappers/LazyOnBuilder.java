package fun.nibaba.lazyfish.mybatis.plus.join.wrappers;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import fun.nibaba.lazyfish.mybatis.plus.join.functions.ValueFunction;
import fun.nibaba.lazyfish.mybatis.plus.join.interfaces.LazyCompare;
import fun.nibaba.lazyfish.mybatis.plus.join.interfaces.LazyJoinCompare;
import fun.nibaba.lazyfish.mybatis.plus.join.interfaces.LazyNested;
import fun.nibaba.lazyfish.mybatis.plus.join.segments.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * On条件
 *
 * @author chenjiamin
 * @date 2021/12/17 10:35 上午
 */
public class LazyOnBuilder<Main, Join> implements
        LazyCompare<LazyOnBuilder<Main, Join>, Join, Object>,
        LazyJoinCompare<LazyOnBuilder<Main, Join>, Main, Join>,
        LazyNested<LazyOnBuilder<Main, Join>> {

    final LazyTable<Main> lazyTable;

    final LazyTable<Join> lazyJoinTable;

    final JoinOnSegment joinOnSegment;

    private LazyOnBuilder(LazyTable<Main> lazyTable, LazyTable<Join> lazyJoinTable) {
        this.lazyTable = lazyTable;
        this.lazyJoinTable = lazyJoinTable;
        this.joinOnSegment = new JoinOnSegment();
    }

    public static <Main, Join> LazyOnBuilder<Main, Join> builder(LazyTable<Main> lazyTable,
                                                                 LazyTable<Join> lazyJoinTable) {
        return new LazyOnBuilder<>(lazyTable, lazyJoinTable);
    }

    @Override
    public LazyOnBuilder<Main,Join> compare(boolean condition, SFunction<Join, ?> column, ValueFunction<Object> value, SqlKeyword sqlKeyword) {
        this.addWhereSegment(column, value.getValue(), sqlKeyword);
        return this;
    }

    @Override
    public LazyOnBuilder<Main,Join> like(boolean condition, SFunction<Join, ?> column, ValueFunction<Object> value, SqlLike sqlLike, SqlKeyword sqlKeyword) {
        this.addWhereSegment(column, SqlUtils.concatLike(value.getValue(), sqlLike), sqlKeyword);
        return this;
    }

    @Override
    public LazyOnBuilder<Main,Join> nullable(boolean condition, SFunction<Join, ?> column, SqlKeyword sqlKeyword) {
        this.addWhereSegment(new CompareSegment(new ColumnSegment(lazyTable.getTableNameAlia(), lazyJoinTable.getColumnName(column)), sqlKeyword));
        return this;
    }

    @Override
    public LazyOnBuilder<Main,Join>containable(boolean condition, SqlKeyword sqlKeyword, SFunction<Join, ?> column, ValueFunction<Collection<?>> collectionFunction) {
        if (!condition) {
            return this;
        }
        Collection<?> collection = collectionFunction.getValue();
        if (collection == null || collection.isEmpty()) {
            return this;
        }
        ColumnCache columnCache = lazyJoinTable.getColumnCache(column);
        if (collection.size() == 1) {
            Iterator<?> iterator = collection.iterator();
            if (sqlKeyword == SqlKeyword.IN) {
                this.eq(column, iterator.next());
            } else if (sqlKeyword == SqlKeyword.NOT_IN) {
                this.ne(column, iterator.next());
            }
        } else {
            String values = collection.stream()
                    .map(value -> this.joinOnSegment.formatParam(lazyTable.getTableNameAlia(), columnCache, value))
                    .collect(Collectors.joining(StringPool.COMMA, StringPool.LEFT_BRACKET, StringPool.RIGHT_BRACKET));
            this.joinOnSegment.add(new CompareValueSegment(new ColumnSegment(lazyTable.getTableNameAlia(), columnCache.getColumnSelect()), sqlKeyword, values));

        }
        return this;
    }

    /**
     * 添加 where sql 片段
     *
     * @param column     列
     * @param value      值
     * @param sqlKeyword sql条件连接关键字
     */
    private void addWhereSegment(SFunction<Join, ?> column, Object value, SqlKeyword sqlKeyword) {
        ColumnCache columnCache = lazyJoinTable.getColumnCache(column);
        String paramValue = this.joinOnSegment.formatParam(lazyTable.getTableNameAlia(), columnCache, value);
        this.joinOnSegment.add(new CompareValueSegment(new ColumnSegment(lazyTable.getTableNameAlia(), columnCache.getColumnSelect()), sqlKeyword, paramValue));
    }

    private void addWhereSegment(ISqlSegment sqlSegment) {
        this.joinOnSegment.add(sqlSegment);
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
