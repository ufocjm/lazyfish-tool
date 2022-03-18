package com.baomidou.mybatisplus.core.conditions.query;

import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.Update;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import fun.nibaba.lazyfish.mybatis.plus.core.exceptions.LazyMybatisPlusException;
import fun.nibaba.lazyfish.mybatis.plus.core.functions.ValueFunction;
import fun.nibaba.lazyfish.mybatis.plus.core.interfaces.LazySet;
import fun.nibaba.lazyfish.utils.CollUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenjiamin
 * @date 2022-03-18 13:46:39
 */
public class LazyLambdaUpdateWrapper<T> extends AbstractLambdaWrapper<T, LazyLambdaUpdateWrapper<T>>
        implements Update<LazyLambdaUpdateWrapper<T>, SFunction<T, ?>>,
        LazyCompareAdapter<LazyLambdaUpdateWrapper<T>, T, Object>,
        LazySet<LazyLambdaUpdateWrapper<T>, T, Object> {

    /**
     * SQL 更新字段内容，例如：name='1', age=2
     */
    private final List<String> sqlSet;

    public LazyLambdaUpdateWrapper() {
        // 如果无参构造函数，请注意实体 NULL 情况 SET 必须有否则 SQL 异常
        this((T) null);
    }

    public LazyLambdaUpdateWrapper(T entity) {
        super.setEntity(entity);
        super.initNeed();
        this.sqlSet = new ArrayList<>();
    }

    public LazyLambdaUpdateWrapper(Class<T> entityClass) {
        super.setEntityClass(entityClass);
        super.initNeed();
        this.sqlSet = new ArrayList<>();
    }

    LazyLambdaUpdateWrapper(T entity, Class<T> entityClass, List<String> sqlSet, AtomicInteger paramNameSeq,
                            Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments, SharedString paramAlias,
                            SharedString lastSql, SharedString sqlComment, SharedString sqlFirst) {
        super.setEntity(entity);
        super.setEntityClass(entityClass);
        this.sqlSet = sqlSet;
        this.paramNameSeq = paramNameSeq;
        this.paramNameValuePairs = paramNameValuePairs;
        this.expression = mergeSegments;
        this.paramAlias = paramAlias;
        this.lastSql = lastSql;
        this.sqlComment = sqlComment;
        this.sqlFirst = sqlFirst;
    }

    @Override
    public LazyLambdaUpdateWrapper<T> set(SFunction<T, ?> column, Object val) {
        return Update.super.set(column, val);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> set(boolean condition, SFunction<T, ?> column, Object val) {
        return Update.super.set(condition, column, val);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> set(boolean condition, SFunction<T, ?> column, Object val, String mapping) {
        return maybeDo(condition, () -> {
            String sql = formatParam(mapping, val);
            sqlSet.add(columnToString(column) + Constants.EQUALS + sql);
        });
    }

    @Override
    public LazyLambdaUpdateWrapper<T> set(boolean condition, SFunction<T, ?> column, ValueFunction<Object> value) {
        if (!condition) {
            return typedThis;
        }
        return this.set(true, column, value.getValue(), null);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> increment(boolean condition, SFunction<T, ?> column, ValueFunction<Number> value) {
        throw new LazyMybatisPlusException("原声mp语法不支持increment");
    }


    @Override
    public LazyLambdaUpdateWrapper<T> setSql(boolean condition, String sql) {
        if (condition && StringUtils.isNotBlank(sql)) {
            sqlSet.add(sql);
        }
        return typedThis;
    }

    @Override
    public String getSqlSet() {
        if (CollectionUtils.isEmpty(sqlSet)) {
            return null;
        }
        return String.join(Constants.COMMA, sqlSet);
    }

    @Override
    protected LazyLambdaUpdateWrapper<T> instance() {
        return new LazyLambdaUpdateWrapper<>(getEntity(), getEntityClass(), null, paramNameSeq, paramNameValuePairs,
                new MergeSegments(), paramAlias, SharedString.emptyString(), SharedString.emptyString(), SharedString.emptyString());
    }

    @Override
    public void clear() {
        super.clear();
        sqlSet.clear();
    }

    @Override
    public LazyLambdaUpdateWrapper<T> compare(boolean condition, SFunction<T, ?> column, ValueFunction<Object> value, SqlKeyword sqlKeyword) {
        if (!condition) {
            return this;
        }
        return this.addCondition(true, column, sqlKeyword, value.getValue());
    }

    @Override
    public LazyLambdaUpdateWrapper<T> like(boolean condition, SFunction<T, ?> column, ValueFunction<Object> value, SqlLike sqlLike, SqlKeyword sqlKeyword) {
        if (!condition) {
            return this;
        }
        return this.likeValue(true, sqlKeyword, column, value.getValue(), sqlLike);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> nullable(boolean condition, SFunction<T, ?> column, SqlKeyword sqlKeyword) {
        if (!condition) {
            return this;
        }
        return this.maybeDo(true, () -> appendSqlSegments(columnToSqlSegment(column), sqlKeyword));
    }

    @Override
    public LazyLambdaUpdateWrapper<T> containable(boolean condition, SqlKeyword sqlKeyword, SFunction<T, ?> column, ValueFunction<Collection<?>> collectionFunction) {
        if (!condition) {
            return this;
        }
        Collection<?> values = collectionFunction.getValue();
        if (CollUtils.isEmpty(values)) {
            return this;
        }
        if (values.size() == 1) {
            return this.addCondition(true, column, sqlKeyword, values.iterator().next());
        } else {
            return maybeDo(true, () -> appendSqlSegments(columnToSqlSegment(column), sqlKeyword, inExpression(values)));
        }
    }

    /**
     * 因自定义方法和AbstractLambdaWrapper继承的父类实现的Compare类的方法相同，如果不重写的情况下会报错，这里默认使用mp的方法，直接调用super
     **/
    @Override
    public LazyLambdaUpdateWrapper<T> eq(SFunction<T, ?> column, Object val) {
        return super.eq(column, val);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> ne(SFunction<T, ?> column, Object val) {
        return super.ne(column, val);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> lt(SFunction<T, ?> column, Object val) {
        return super.lt(column, val);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> le(SFunction<T, ?> column, Object val) {
        return super.le(column, val);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> gt(SFunction<T, ?> column, Object val) {
        return super.gt(column, val);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> ge(SFunction<T, ?> column, Object val) {
        return super.ge(column, val);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> like(SFunction<T, ?> column, Object val) {
        return super.like(column, val);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> notLike(SFunction<T, ?> column, Object val) {
        return super.notLike(column, val);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> in(SFunction<T, ?> column, Collection<?> coll) {
        return super.in(column, coll);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> notIn(SFunction<T, ?> column, Collection<?> coll) {
        return super.notIn(column, coll);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> isNull(SFunction<T, ?> column) {
        return super.isNull(column);
    }

    @Override
    public LazyLambdaUpdateWrapper<T> isNotNull(SFunction<T, ?> column) {
        return super.isNotNull(column);
    }
}
