package fun.nibaba.database.mybatis.plus.segments;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * 排序 sql 片段
 *
 * @author chenjiamin
 * @date 2021/6/4 5:05 下午
 */
public class OrderBySegment extends ArrayList<OrderSegment> implements NibabaSqlSegment {

    @Override
    public String getSqlSegment() {
        if (this.isEmpty()) {
            return null;
        }
        return SqlKeyword.ORDER_BY.getSqlSegment() + this.stream().map(ISqlSegment::getSqlSegment).collect(Collectors.joining(COMMA));
    }

}
