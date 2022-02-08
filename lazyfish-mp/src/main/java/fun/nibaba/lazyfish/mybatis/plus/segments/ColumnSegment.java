package fun.nibaba.lazyfish.mybatis.plus.segments;

import fun.nibaba.lazyfish.utils.StrUtils;

/**
 * 字段 sql 片段
 *
 * @author chenjiamin
 * @date 2021/6/4 5:14 下午
 */
public class ColumnSegment implements LazySqlSegment {

    private final String tableNameAlias;

    private final String filedName;

    public ColumnSegment(String tableNameAlias, String filedName) {
        this.tableNameAlias = tableNameAlias;
        this.filedName = filedName;
    }

    @Override
    public String getSqlSegment() {
        String sqlSegment = "";
        if (StrUtils.isNotBlank(this.tableNameAlias)) {
            sqlSegment = this.tableNameAlias + DOT;
        }
        sqlSegment += this.filedName;
        return sqlSegment;
    }

}
