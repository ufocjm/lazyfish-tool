package fun.nibaba.lazyfish.mybatis.plus.join.wrappers;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import fun.nibaba.lazyfish.mybatis.plus.join.functions.ValueFunction;
import fun.nibaba.lazyfish.mybatis.plus.join.interfaces.LazyCompare;
import fun.nibaba.lazyfish.mybatis.plus.join.interfaces.LazyNested;
import fun.nibaba.lazyfish.mybatis.plus.join.segments.BracketSegment;
import fun.nibaba.lazyfish.mybatis.plus.join.segments.ColumnSegment;
import fun.nibaba.lazyfish.mybatis.plus.join.segments.CompareSegment;
import fun.nibaba.lazyfish.mybatis.plus.join.segments.WhereSegment;

import java.util.Collection;
import java.util.function.Consumer;


/**
 * 条件构造器
 *
 * @author chenjiamin
 * @date 2021/12/14 3:57 下午
 */
public class LazyWhereBuilder<TableModel> implements
        LazyCompare<LazyWhereBuilder<TableModel>, TableModel, Object>,
        LazyNested<LazyWhereBuilder<TableModel>>,
        Constants {

    final LazyTable<TableModel> lazyTable;

    final WhereSegment whereSegment;


    private LazyWhereBuilder(LazyTable<TableModel> lazyTable, WhereSegment whereSegment) {
        this.lazyTable = lazyTable;
        this.whereSegment = whereSegment;
    }

    public static <TableModel> LazyWhereBuilder<TableModel> builder(LazyTable<TableModel> lazyTable, WhereSegment whereSegment) {
        return new LazyWhereBuilder<>(lazyTable, whereSegment);
    }


    @Override
    public LazyWhereBuilder<TableModel> compare(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Object> value, SqlKeyword sqlKeyword) {
        this.addWhereSegment(column, value.getValue(), sqlKeyword);
        return this;
    }

    @Override
    public LazyWhereBuilder<TableModel> like(boolean condition, SFunction<TableModel, ?> column, ValueFunction<Object> value, SqlLike sqlLike, SqlKeyword sqlKeyword) {
        this.addWhereSegment(column, SqlUtils.concatLike(value.getValue(), sqlLike), sqlKeyword);
        return this;
    }

    @Override
    public LazyWhereBuilder<TableModel> nullable(boolean condition, SFunction<TableModel, ?> column, SqlKeyword sqlKeyword) {
        this.addWhereSegment(new CompareSegment(new ColumnSegment(lazyTable.getTableNameAlia(), lazyTable.getColumnName(column)), sqlKeyword));
        return this;
    }

    @Override
    public LazyWhereBuilder<TableModel> nested(boolean condition, Consumer<LazyWhereBuilder<TableModel>> consumer, SqlKeyword sqlKeyword) {
        if (!condition) {
            return this;
        }

        this.addWhereSegment(sqlKeyword);
        if (consumer == null) {
            return this;
        }
        LazyWhereBuilder<TableModel> lazyWhere = new LazyWhereBuilder<>(this.lazyTable, new WhereSegment(this.whereSegment));
        consumer.accept(lazyWhere);
        this.addWhereSegment(new BracketSegment(lazyWhere.whereSegment));

        return this;
    }


    @Override
    public LazyWhereBuilder<TableModel> containable(boolean condition, SqlKeyword sqlKeyword, SFunction<TableModel, ?> column, Collection<?> collection) {
        if (!condition) {
            return this;
        }
        if (collection == null || collection.isEmpty()) {
            return this;
        }
        ColumnCache columnCache = lazyTable.getColumnCache(column);
        this.whereSegment.addCollection(lazyTable.getTableNameAlia(), columnCache, sqlKeyword, collection);
        return this;
    }

    /**
     * 添加 where sql 片段
     *
     * @param column     列
     * @param value      值
     * @param sqlKeyword sql条件连接关键字
     */
    private void addWhereSegment(SFunction<TableModel, ?> column, Object value, SqlKeyword sqlKeyword) {
        ColumnCache columnCache = lazyTable.getColumnCache(column);
        this.whereSegment.add(lazyTable.getTableNameAlia(), columnCache, sqlKeyword, value);
    }

    private void addWhereSegment(ISqlSegment sqlSegment) {
        this.whereSegment.add(sqlSegment);
    }

}

