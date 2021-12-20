package fun.nibaba.lazyfish.mybatis.plus.join.wrappers;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.util.HashMap;
import java.util.Map;

/**
 * 列对象
 * 主要用于获取列
 *
 * @author chenjiamin
 * @date 2021/12/14 4:23 下午
 */
public class LazyColumn<TableModel> {

    protected final Class<TableModel> tableClass;

    protected final TableInfo tableInfo;

    protected final String tableName;

    protected final String tableNameAlia;

    /**
     * column cache map
     * Map<className,<fieldName,columnCache>>
     */
    protected static Map<String, Map<String, ColumnCache>> classColumnMap = new HashMap<>();

    protected LazyColumn(Class<TableModel> tableClass, String tableNameAlia) {
        this.tableClass = tableClass;
        this.tableInfo = TableInfoHelper.getTableInfo(tableClass);
        this.tableName = tableInfo.getTableName();
        if (StrUtil.isBlank(tableNameAlia)) {
            this.tableNameAlia = this.tableName;
        } else {
            this.tableNameAlia = tableNameAlia;
        }
    }

    protected ColumnCache getColumnCache(SFunction<TableModel, ?> column) {
        LambdaMeta meta = LambdaUtils.extract(column);
        String fieldName = PropertyNamer.methodToProperty(meta.getImplMethodName());
        this.tryInitCache(meta.getInstantiatedClass());
        return this.getColumnCache(fieldName, meta.getInstantiatedClass());
    }

    private void tryInitCache(Class<?> lambdaClass) {
        if (!classColumnMap.containsKey(lambdaClass.getName())) {
            Map<String, ColumnCache> columnMap = LambdaUtils.getColumnMap(lambdaClass);
            classColumnMap.put(lambdaClass.getName(), columnMap);
        }
    }

    private ColumnCache getColumnCache(String fieldName, Class<?> lambdaClass) {
        Map<String, ColumnCache> columnMap = classColumnMap.get(lambdaClass.getName());
        ColumnCache columnCache = columnMap.get(LambdaUtils.formatKey(fieldName));
        Assert.notNull(columnCache, "can not find lambda cache for this property [%s] of entity [%s]",
                fieldName, lambdaClass.getName());
        return columnCache;
    }

    protected String getTableName() {
        return this.tableName;
    }

    protected String getTableNameAlia() {
        return this.tableNameAlia;
    }

    protected String getColumnName(SFunction<TableModel, ?> column) {
        ColumnCache columnCache = this.getColumnCache(column);
        return columnCache.getColumnSelect();
    }

}
