package fun.lazyfish.database.mybatis.plus.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Nibaba MybatisPlus 支持 SQL 方法
 *
 * @author chenjiamin
 * @date 2021/5/31 4:44 下午
 */
@AllArgsConstructor
@Getter
public enum NibabaSqlMethod {

    /**
     * 关联列表查询
     * 1.表名
     * 2。
     */
    JOIN_SELECT_LIST("joinSelectList", "join查询满足条件所有数据", "<script>SELECT %s FROM %s %s %s %s %s %s %s\n</script>"),

    JOIN_SELECT("joinSelect", "join查询满足条件单条", "<script>SELECT %s FROM %s %s %s %s %s %s %s\n</script>"),

    JOIN_SELECT_PAGE("joinSelectPage", "join查询满足条件所有数据", "<script>SELECT %s FROM %s %s %s %s %s %s %s\n</script>");

    private final String method;

    private final String desc;

    private final String sql;

}
