package fun.nibaba.lazyfish.mybatis.plus.core.wrappers;

import fun.nibaba.lazyfish.mybatis.plus.core.segments.LazyParamMap;
import fun.nibaba.lazyfish.mybatis.plus.core.segments.SetSegment;
import fun.nibaba.lazyfish.mybatis.plus.core.segments.WhereSegment;
import lombok.Getter;

import java.util.Map;

/**
 * 修改语句构建器
 *
 * @author chenjiamin
 * @date 2022-03-14 16:23:19
 */
public class LazyUpdateWrapper {


    /**
     * tableName as
     */
    @Getter
    private final String tableNameAlias;

    /**
     * set
     */
    private final SetSegment setSegment;

    /**
     * where
     */
    private final WhereSegment whereSegment;

    /**
     * paramMap
     */
    private final LazyParamMap paramMap;

    LazyUpdateWrapper(String tableNameAlias, SetSegment setSegment, WhereSegment whereSegment, LazyParamMap paramMap) {
        this.tableNameAlias = tableNameAlias;
        this.setSegment = setSegment;
        this.whereSegment = whereSegment;
        this.paramMap = paramMap;
    }

    /**
     * 生成 builder
     *
     * @param lazyTable    表
     * @param <TableModel> 表类型
     * @return builder
     */
    public static <TableModel> LazyUpdateWrapperBuilder<TableModel> builder(LazyTable<TableModel> lazyTable) {
        return LazyUpdateWrapperBuilder.builder(lazyTable);
    }

    public String getSetSegment() {
        return setSegment.getSqlSegment();
    }

    public String getWhereSegment() {
        return whereSegment != null ? whereSegment.getSqlSegment() : null;
    }

    /**
     * 提供给Mybatis使用的get方法
     *
     * @return where条件的所有值的键值对
     */
    public Map<String, Object> getParamNameValuePairs() {
        return this.paramMap;
    }

}
