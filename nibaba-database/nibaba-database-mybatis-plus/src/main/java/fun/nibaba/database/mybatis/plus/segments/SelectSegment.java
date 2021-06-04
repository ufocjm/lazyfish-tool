package fun.nibaba.database.mybatis.plus.segments;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * select table_filed sql segment
 *
 * @author chenjiamin
 * @date 2021/6/3 4:03 下午
 */
@Getter
@AllArgsConstructor
public class SelectSegment extends ArrayList<ISqlSegment> implements NibabaSqlSegment {

    private final String tableAlias;

    @Override
    public String getSqlSegment() {
        if (this.isEmpty()) {
            return null;
        }
        return this.stream().map(sqlSegment -> this.tableAlias + DOT + sqlSegment.getSqlSegment()).collect(Collectors.joining(COMMA));
    }

}
