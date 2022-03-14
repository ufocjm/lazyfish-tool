package fun.nibaba.lazyfish.mybatis.plus.core.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import fun.nibaba.lazyfish.mybatis.plus.core.enums.LazySqlMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Map;

/**
 * 关联列表查询
 *
 * @author chenjiamin
 * @date 2021/5/31 4:28 下午
 */
public class LazyUpdate extends AbstractMethod {

    // WRAPPER_DOT = "ew."
    private final static String WRAPPER_TABLE_NAME_ALIAS = WRAPPER_DOT + "tableNameAlias";

    private final static String SET_SEGMENT = "setSegment";

    private final static String WRAPPER_SET_SEGMENT = WRAPPER_DOT + SET_SEGMENT;

    private final static String WHERE_SEGMENT = "whereSegment";

    private final static String WRAPPER_WHERE_SEGMENT = WRAPPER_DOT + WHERE_SEGMENT;


    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        LazySqlMethod sqlMethod = LazySqlMethod.LAZY_UPDATE;
        String sql = String.format(sqlMethod.getSql(),
                tableInfo.getTableName(),
                this.tableNameAlias(),
                this.set(),
                this.where()
        );
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addUpdateMappedStatement(mapperClass, modelClass, sqlMethod.getMethod(), sqlSource);
    }

    /**
     * set语句
     *
     * @return set
     */
    private String set() {
        String sqlScript = SqlScriptUtils.convertIf(SqlScriptUtils.unSafeParam(WRAPPER_SET_SEGMENT), String.format("%s != null and %s != null", WRAPPER, WRAPPER_SET_SEGMENT), true);
        return SqlScriptUtils.convertSet(sqlScript);
    }

    /**
     * 表别名
     *
     * @return 表别名
     */
    protected String tableNameAlias() {
        return AS + SqlScriptUtils.unSafeParam(WRAPPER_TABLE_NAME_ALIAS);
    }


    /**
     * where 条件
     *
     * @return where 条件
     */
    protected String where() {
        String sqlScript = SqlScriptUtils.convertIf(SqlScriptUtils.unSafeParam(WRAPPER_WHERE_SEGMENT), String.format("%s != null and %s != null", WRAPPER, WRAPPER_WHERE_SEGMENT), true);
        return SqlScriptUtils.convertWhere(sqlScript);
    }

}
