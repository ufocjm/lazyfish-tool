package fun.nibaba.database.mybatis.plus.segments;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * where 条件 sql 片段
 *
 * @author chenjiamin
 * @date 2021/6/2 4:10 下午
 */
public class JoinWhereSegment extends ArrayList<ISqlSegment> implements NibabaSqlSegment {

    @Override
    public String getSqlSegment() {
        return this.stream().map(ISqlSegment::getSqlSegment).collect(Collectors.joining(SPACE));
    }

}
