package fun.nibaba.database.mybatis.plus.model;

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
public class NibabaTableInfo implements Constants {

    private final TableInfo tableInfo;

    private final String tableNameAlias;


    @Builder
    public NibabaTableInfo(TableInfo tableInfo, String tableNameAlias) {
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
