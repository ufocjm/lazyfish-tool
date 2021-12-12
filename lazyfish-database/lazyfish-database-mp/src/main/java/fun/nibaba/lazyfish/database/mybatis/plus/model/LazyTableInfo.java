package fun.nibaba.lazyfish.database.mybatis.plus.model;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import lombok.Builder;
import lombok.Getter;

/**
 * table info
 *
 * @author chenjiamin
 * @date 2021/6/1 5:36 下午
 */
@Getter
public class LazyTableInfo implements Constants {

    private final TableInfo tableInfo;

    /**
     * 表别名
     */
    private final String tableNameAlias;


    @Builder
    public LazyTableInfo(TableInfo tableInfo, String tableNameAlias) {
        this.tableInfo = tableInfo;
        if (StrUtil.isNotBlank(tableNameAlias)) {
            this.tableNameAlias = tableNameAlias;
        } else {
            this.tableNameAlias = tableInfo.getTableName();
        }
    }

    public String getTableNameAlias() {
        return tableNameAlias;
    }
}
