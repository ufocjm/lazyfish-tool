/*
 * Copyright (c) 2011-2021, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baomidou.mybatisplus.core.conditions.query;

import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import fun.nibaba.lazyfish.mybatis.plus.core.functions.ValueFunction;
import fun.nibaba.lazyfish.utils.CollUtils;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * Lambda 语法使用 Wrapper
 *
 * @author Chen Jiamin
 * @since 2022-03-15
 */
@SuppressWarnings("serial")
public class LazyLambdaQueryWrapper<T> extends AbstractLambdaWrapper<T, LazyLambdaQueryWrapper<T>>
        implements Query<LazyLambdaQueryWrapper<T>, T, SFunction<T, ?>>,
        LazyCompareAdapter<LazyLambdaQueryWrapper<T>, T, Object> {


    /**
     * 查询字段
     */
    private SharedString sqlSelect = new SharedString();

    public LazyLambdaQueryWrapper() {
        this((T) null);
    }

    public LazyLambdaQueryWrapper(T entity) {
        super.setEntity(entity);
        super.initNeed();
    }

    public LazyLambdaQueryWrapper(Class<T> entityClass) {
        super.setEntityClass(entityClass);
        super.initNeed();
    }

    LazyLambdaQueryWrapper(T entity, Class<T> entityClass, SharedString sqlSelect, AtomicInteger paramNameSeq,
                           Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments, SharedString paramAlias,
                           SharedString lastSql, SharedString sqlComment, SharedString sqlFirst) {
        super.setEntity(entity);
        super.setEntityClass(entityClass);
        this.paramNameSeq = paramNameSeq;
        this.paramNameValuePairs = paramNameValuePairs;
        this.expression = mergeSegments;
        this.sqlSelect = sqlSelect;
        this.paramAlias = paramAlias;
        this.lastSql = lastSql;
        this.sqlComment = sqlComment;
        this.sqlFirst = sqlFirst;
    }

    /**
     * SELECT 部分 SQL 设置
     *
     * @param columns 查询字段
     */
    @SafeVarargs
    @Override
    public final LazyLambdaQueryWrapper<T> select(SFunction<T, ?>... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            this.sqlSelect.setStringValue(columnsToString(false, columns));
        }
        return typedThis;
    }

    /**
     * 过滤查询的字段信息(主键除外!)
     * <p>例1: 只要 java 字段名以 "test" 开头的             -> select(i -&gt; i.getProperty().startsWith("test"))</p>
     * <p>例2: 只要 java 字段属性是 CharSequence 类型的     -> select(TableFieldInfo::isCharSequence)</p>
     * <p>例3: 只要 java 字段没有填充策略的                 -> select(i -&gt; i.getFieldFill() == FieldFill.DEFAULT)</p>
     * <p>例4: 要全部字段                                   -> select(i -&gt; true)</p>
     * <p>例5: 只要主键字段                                 -> select(i -&gt; false)</p>
     *
     * @param predicate 过滤方式
     * @return this
     */
    @Override
    public LazyLambdaQueryWrapper<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
        if (entityClass == null) {
            entityClass = getEntityClass();
        } else {
            setEntityClass(entityClass);
        }
        Assert.notNull(entityClass, "entityClass can not be null");
        this.sqlSelect.setStringValue(TableInfoHelper.getTableInfo(entityClass).chooseSelect(predicate));
        return typedThis;
    }

    @Override
    public String getSqlSelect() {
        return sqlSelect.getStringValue();
    }

    /**
     * 用于生成嵌套 sql
     * <p>故 sqlSelect 不向下传递</p>
     */
    @Override
    protected LazyLambdaQueryWrapper<T> instance() {
        return new LazyLambdaQueryWrapper<>(getEntity(), getEntityClass(), null, paramNameSeq, paramNameValuePairs,
                new MergeSegments(), paramAlias, SharedString.emptyString(), SharedString.emptyString(), SharedString.emptyString());
    }

    @Override
    public void clear() {
        super.clear();
        sqlSelect.toNull();
    }

    @Override
    public LazyLambdaQueryWrapper<T> compare(boolean condition, SFunction<T, ?> column, ValueFunction<Object> value, SqlKeyword sqlKeyword) {
        if (!condition) {
            return this;
        }
        return this.addCondition(true, column, sqlKeyword, value.getValue());
    }

    @Override
    public LazyLambdaQueryWrapper<T> like(boolean condition, SFunction<T, ?> column, ValueFunction<Object> value, SqlLike sqlLike, SqlKeyword sqlKeyword) {
        if (!condition) {
            return this;
        }
        return this.likeValue(true, sqlKeyword, column, value.getValue(), sqlLike);
    }

    @Override
    public LazyLambdaQueryWrapper<T> nullable(boolean condition, SFunction<T, ?> column, SqlKeyword sqlKeyword) {
        if (!condition) {
            return this;
        }
        return this.maybeDo(true, () -> appendSqlSegments(columnToSqlSegment(column), sqlKeyword));
    }

    @Override
    public LazyLambdaQueryWrapper<T> containable(boolean condition, SqlKeyword sqlKeyword, SFunction<T, ?> column, ValueFunction<Collection<?>> collectionFunction) {
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

    /**因自定义方法和AbstractLambdaWrapper继承的父类实现的Compare类的方法相同，如果不重写的情况下会报错，这里默认使用mp的方法，直接调用super**/
    @Override
    public LazyLambdaQueryWrapper<T> eq(SFunction<T, ?> column, Object val) {
        return super.eq(column, val);
    }

    @Override
    public LazyLambdaQueryWrapper<T> ne(SFunction<T, ?> column, Object val) {
        return super.ne(column, val);
    }

    @Override
    public LazyLambdaQueryWrapper<T> lt(SFunction<T, ?> column, Object val) {
        return super.lt(column, val);
    }

    @Override
    public LazyLambdaQueryWrapper<T> le(SFunction<T, ?> column, Object val) {
        return super.le(column, val);
    }

    @Override
    public LazyLambdaQueryWrapper<T> gt(SFunction<T, ?> column, Object val) {
        return super.gt(column, val);
    }

    @Override
    public LazyLambdaQueryWrapper<T> ge(SFunction<T, ?> column, Object val) {
        return super.ge(column, val);
    }

    @Override
    public LazyLambdaQueryWrapper<T> like(SFunction<T, ?> column, Object val) {
        return super.like(column, val);
    }

    @Override
    public LazyLambdaQueryWrapper<T> notLike(SFunction<T, ?> column, Object val) {
        return super.notLike(column, val);
    }

    @Override
    public LazyLambdaQueryWrapper<T> in(SFunction<T, ?> column, Collection<?> coll) {
        return super.in(column, coll);
    }

    @Override
    public LazyLambdaQueryWrapper<T> notIn(SFunction<T, ?> column, Collection<?> coll) {
        return super.notIn(column, coll);
    }

    @Override
    public LazyLambdaQueryWrapper<T> isNull(SFunction<T, ?> column) {
        return super.isNull(column);
    }

    @Override
    public LazyLambdaQueryWrapper<T> isNotNull(SFunction<T, ?> column) {
        return super.isNotNull(column);
    }

}
