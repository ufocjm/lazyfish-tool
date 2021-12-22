package fun.nibaba.lazyfish.mybatis.plus.join.wrappers;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.ibatis.reflection.ReflectionException;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    private static final Map<Class<?>, Class<?>> FUNC_CACHE = new ConcurrentHashMap<>();

    private static final Map<Class<?>, SerializedLambda> LAMBDA_CACHE = new ConcurrentHashMap<>();

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
        SerializedLambda serializedLambda = LAMBDA_CACHE.get(column.getClass());
        if (serializedLambda == null) {
            serializedLambda = this.getSerializedLambda(column);
            LAMBDA_CACHE.put(column.getClass(), serializedLambda);
        }

        Class<?> clazz = FUNC_CACHE.get(column.getClass());
        if (clazz == null) {
            clazz = this.getClazz(serializedLambda);
            FUNC_CACHE.put(column.getClass(), clazz);
        }

        String fieldName = this.getFiledName(serializedLambda);

        this.tryInitCache(clazz);
        return this.getColumnCache(fieldName, clazz);
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

    private String getFiledName(SerializedLambda serializedLambda) {
        // 从lambda信息取出method、field、class等
        String methodName = serializedLambda.getImplMethodName();
        return this.methodToProperty(methodName);
    }

    private <T> SerializedLambda getSerializedLambda(SFunction<T, ?> column) {
        Method writeReplaceMethod;
        try {
            writeReplaceMethod = column.getClass().getDeclaredMethod("writeReplace");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        // 从序列化方法取出序列化的lambda信息
        writeReplaceMethod.setAccessible(true);
        try {
            return (SerializedLambda) writeReplaceMethod.invoke(column);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private String methodToProperty(String methodName) {
        if (methodName.startsWith("is")) {
            methodName = methodName.substring(2);
        } else if (methodName.startsWith("get") || methodName.startsWith("set")) {
            methodName = methodName.substring(3);
        } else {
            throw new ReflectionException("转换字段名失败,没有找到is/get/set方法");
        }

        if (methodName.length() == 1 || (methodName.length() > 1 && !Character.isUpperCase(methodName.charAt(1)))) {
            methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
        }

        return methodName;
    }

    private Class<?> getClazz(SerializedLambda serializedLambda) {
        try {
            return Class.forName(serializedLambda.getImplClass().replace("/", "."));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
