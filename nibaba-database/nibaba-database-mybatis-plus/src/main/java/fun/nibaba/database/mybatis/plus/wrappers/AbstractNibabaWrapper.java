package fun.nibaba.database.mybatis.plus.wrappers;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.google.common.collect.Maps;
import fun.nibaba.database.mybatis.plus.functions.ValueFunction;
import fun.nibaba.database.mybatis.plus.interfaces.NibabaCompare;
import fun.nibaba.database.mybatis.plus.interfaces.NibabaNested;
import fun.nibaba.database.mybatis.plus.interfaces.NibabaQuery;
import fun.nibaba.database.mybatis.plus.model.NibabaTableInfo;
import fun.nibaba.database.mybatis.plus.segments.*;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * sql构造器
 *
 * @author chenjiamin
 * @date 2021/6/1 5:23 下午
 */
public abstract class AbstractNibabaWrapper<MainTableClass, Child extends AbstractNibabaWrapper<MainTableClass, Child>>
        implements Constants,
        NibabaCompare<MainTableClass, Child, Object>,
        NibabaQuery<MainTableClass, Child>,
        NibabaNested<Child> {

    /**
     * 实体对象class
     */
    protected Class<MainTableClass> entityClass;

    /**
     * 当前对象
     */
    protected Child typeThis = (Child) this;

    /**
     * 表信息
     */
    protected final NibabaTableInfo tableInfo;

    /**
     * 表seq
     */
    protected final int tableSeq;

    /**
     * 条件具体的值
     */
    protected final Map<String, Object> paramNameValuePairs;

    /**
     * 查询条件排序
     */
    protected final AtomicInteger paramNameSeq;

    /**
     * select table_field sql 片段
     */
    protected final SelectSegment selectSegment;

    /**
     * 条件sql片段 但是不包含值 只有最终生成的 xml当中的语句
     * t.id = #{ew.paramNameValuePairs.id}
     */
    protected final WhereSegment whereSegment;

    /**
     * 末尾sql片段
     */
    protected String lastSqlSegment;

    /**
     * column cache map
     * Map<className,<fieldName,columnCache>>
     */
    protected Map<String, Map<String, ColumnCache>> classColumnMap;

    public AbstractNibabaWrapper(Class<MainTableClass> mainTableClass, int tableSeq) {
        this(mainTableClass, null, tableSeq, Maps.newHashMap());
    }

    public AbstractNibabaWrapper(Class<MainTableClass> mainTableClass, String tableNameAlias, int tableSeq) {
        this(mainTableClass, tableNameAlias, tableSeq, Maps.newHashMap());
    }

    public AbstractNibabaWrapper(Class<MainTableClass> mainTableClass,
                                 String tableNameAlias,
                                 int tableSeq, Map<String, Object> paramNameValuePairs) {
        this(mainTableClass, tableNameAlias, tableSeq, new AtomicInteger(0), new WhereSegment(), paramNameValuePairs, null);
    }

    public AbstractNibabaWrapper(Class<MainTableClass> mainTableClass,
                                 String tableNameAlias,
                                 int tableSeq,
                                 AtomicInteger paramNameSeq,
                                 WhereSegment whereSegment,
                                 Map<String, Object> paramNameValuePairs,
                                 Map<String, Map<String, ColumnCache>> classColumnMap) {
        if (mainTableClass == null) {
            throw new RuntimeException("不存在泛型无法进行下一步操作");
        }
        TableInfo tableInfo = TableInfoHelper.getTableInfo(mainTableClass);
        if (tableInfo == null) {
            throw new RuntimeException("不存在[" + mainTableClass.getName() + "]的TableInfo实体");
        }
        this.entityClass = mainTableClass;
        this.tableInfo = NibabaTableInfo.builder()
                .tableNameAlias(tableNameAlias)
                .tableInfo(tableInfo)
                .build();
        this.tableSeq = tableSeq;
        this.paramNameSeq = paramNameSeq;
        this.selectSegment = new SelectSegment(this.getTableNameAlias());
        this.whereSegment = whereSegment;
        this.paramNameValuePairs = paramNameValuePairs;
        this.classColumnMap = classColumnMap != null ? classColumnMap : Maps.newHashMap();
    }

    public String getTableName() {
        return this.tableInfo.getTableInfo().getTableName() + AS + this.getTableNameAlias();
    }

    /**
     * 查询字段
     *
     * @return 组装好的字段 例:t.id,t1.name
     */
    public abstract String getSqlSelect();

    /**
     * 复制一份当前对象
     * 用于嵌套sql
     *
     * @return 当前对象
     */
    public abstract Child instance();

    /**
     * 表别名
     *
     * @return 表别名
     */
    public String getTableNameAlias() {
        return this.tableInfo.getTableNameAlias();
    }

    /**
     * 条件sql片段
     *
     * @return where条件
     */
    public String getWhereSegment() {
        if (whereSegment == null) {
            return EMPTY;
        }
        return whereSegment.getSqlSegment();
    }

    /**
     * 强制增加在末尾的sql
     *
     * @return 末尾sql
     */
    public String getLastSqlSegment() {
        return this.lastSqlSegment;
    }

    /**
     * 强制增加在末尾的sql
     *
     * @return this
     */
    public Child lastSql(String lastSql) {
        this.lastSqlSegment = lastSql;
        return typeThis;
    }

    @Override
    public Child select(SFunction<MainTableClass, ?> column) {
        ColumnCache columnCache = this.getColumnCache(column);
        selectSegment.add(columnCache::getColumnSelect);
        return typeThis;
    }

    @Override
    public Child eq(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Object> value) {
        if (condition) {
            this.addWhereSegment(column, value.getValue(), SqlKeyword.EQ);
        }
        return typeThis;
    }

    @Override
    public Child ne(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Object> value) {
        if (condition) {
            this.addWhereSegment(column, value.getValue(), SqlKeyword.NE);
        }
        return typeThis;
    }

    @Override
    public Child gt(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Object> value) {
        if (condition) {
            this.addWhereSegment(column, value.getValue(), SqlKeyword.GT);
        }
        return typeThis;
    }

    @Override
    public Child ge(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Object> value) {
        if (condition) {
            this.addWhereSegment(column, value.getValue(), SqlKeyword.GE);
        }
        return typeThis;
    }

    @Override
    public Child lt(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Object> value) {
        if (condition) {
            this.addWhereSegment(column, value.getValue(), SqlKeyword.LT);
        }
        return typeThis;
    }

    @Override
    public Child le(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Object> value) {
        if (condition) {
            this.addWhereSegment(column, value.getValue(), SqlKeyword.LE);
        }
        return typeThis;
    }

    @Override
    public Child like(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Object> value) {
        if (condition) {
            this.addWhereSegment(column, SqlUtils.concatLike(value.getValue(), SqlLike.DEFAULT), SqlKeyword.LIKE);
        }
        return typeThis;
    }

    @Override
    public Child leftLike(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Object> value) {
        if (condition) {
            this.addWhereSegment(column, SqlUtils.concatLike(value.getValue(), SqlLike.LEFT), SqlKeyword.LIKE);
        }
        return typeThis;
    }

    @Override
    public Child rightLike(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Object> value) {
        if (condition) {
            this.addWhereSegment(column, SqlUtils.concatLike(value.getValue(), SqlLike.RIGHT), SqlKeyword.LIKE);
        }
        return typeThis;
    }

    @Override
    public Child notLike(boolean condition, SFunction<MainTableClass, ?> column, ValueFunction<Object> value) {
        if (condition) {
            this.addWhereSegment(column, SqlUtils.concatLike(value.getValue(), SqlLike.DEFAULT), SqlKeyword.NOT_LIKE);
        }
        return typeThis;
    }

    @Override
    public Child nested(boolean condition, Consumer<Child> consumer, SqlKeyword sqlKeyword) {
        if (!condition) {
            return typeThis;
        }
        this.addWhereSegment(sqlKeyword);
        if (consumer == null) {
            return typeThis;
        }
        Child instance = this.instance();
        consumer.accept(instance);
        this.addWhereSegment(new BracketSegment(instance.whereSegment));
        return typeThis;
    }

    /**
     * 添加 where sql 片段
     *
     * @param column     列
     * @param value      值
     * @param sqlKeyword sql条件连接关键字
     */
    protected void addWhereSegment(SFunction<MainTableClass, ?> column, Object value, SqlKeyword sqlKeyword) {
        ColumnCache columnCache = this.getColumnCache(column);
        String paramValue = this.formatParam(columnCache, value);
        this.addWhereSegment(new CompareValueSegment(new ColumnSegment(this.getTableNameAlias(), columnCache.getColumnSelect()), sqlKeyword, paramValue));
    }

    /**
     * 添加 where sql 片段
     *
     * @param sqlSegment sql片段
     */
    protected void addWhereSegment(ISqlSegment sqlSegment) {
        this.whereSegment.add(sqlSegment);
    }

    /**
     * 格式化参数,并且把取值方式放入 Map中
     *
     * @param columnCache 列缓存
     * @param value       值
     * @return 获取 paramNameValuePairs 的key值 #{eq.paramNameValuePairs.keyName}
     */
    private String formatParam(ColumnCache columnCache, Object value) {
        String paramKey = this.tableInfo.getTableInfo().getTableName() + UNDERSCORE + this.tableSeq + UNDERSCORE + columnCache.getColumn() + UNDERSCORE + this.paramNameSeq.incrementAndGet();
        String paramValue = WRAPPER + WRAPPER_PARAM_MIDDLE + paramKey;
        this.paramNameValuePairs.put(paramKey, value);
        return SqlScriptUtils.safeParam(paramValue, columnCache.getMapping());
    }

    /**
     * 获取 SerializedLambda 对应的列信息，从 lambda 表达式中推测实体类
     * <p>
     * 如果获取不到列信息，那么本次条件组装将会失败
     *
     * @return 列
     * @throws com.baomidou.mybatisplus.core.exceptions.MybatisPlusException 获取不到列信息时抛出异常
     */
    protected ColumnCache getColumnCache(SFunction<?, ?> column) {
        LambdaMeta meta = LambdaUtils.extract(column);
        String fieldName = PropertyNamer.methodToProperty(meta.getImplMethodName());
        this.tryInitCache(meta.getInstantiatedClass());
        return this.getColumnCache(fieldName, meta.getInstantiatedClass());
    }

    private void tryInitCache(Class<?> lambdaClass) {
        if (!classColumnMap.containsKey(lambdaClass.getName())) {
//            final Class<?> entityClass = this.tableInfo.getTableInfo().getEntityType();
//            if (entityClass != null) {
//                lambdaClass = entityClass;
//            }
            Map<String, ColumnCache> columnMap = LambdaUtils.getColumnMap(lambdaClass);
            Assert.notNull(this.classColumnMap, "can not find lambda cache for this entity [%s]", lambdaClass.getName());
            this.classColumnMap.put(lambdaClass.getName(), columnMap);
        }
    }

    private ColumnCache getColumnCache(String fieldName, Class<?> lambdaClass) {
        Map<String, ColumnCache> columnMap = classColumnMap.get(lambdaClass.getName());
        ColumnCache columnCache = columnMap.get(LambdaUtils.formatKey(fieldName));
        Assert.notNull(columnCache, "can not find lambda cache for this property [%s] of entity [%s]",
                fieldName, lambdaClass.getName());
        return columnCache;
    }

}
