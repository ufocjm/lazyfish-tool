package fun.nibaba.database.mybatis.plus.segments;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.conditions.segments.MatchSegment;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * where 条件 sql 片段
 *
 * @author chenjiamin
 * @date 2021/6/2 4:10 下午
 */
public class WhereSegment extends ArrayList<ISqlSegment> implements NibabaSqlSegment {

    /**
     * 最后一个元素
     */
    private ISqlSegment lastValue;

    @Override
    public String getSqlSegment() {
        this.flushFirstValue();
        return this.stream().map(ISqlSegment::getSqlSegment).collect(Collectors.joining(SPACE));
    }

    @Override
    public boolean add(ISqlSegment sqlSegment) {
        boolean matchLastValueJoinKey = MatchSegment.AND_OR.match(this.lastValue);
        boolean matchValueJoinKey = MatchSegment.AND_OR.match(sqlSegment);
        if (matchLastValueJoinKey) {
            if (matchValueJoinKey) {
                this.removeLastValue();
            }
        } else {
            if (!matchValueJoinKey) {
                super.add(SqlKeyword.AND);
            }
        }
        super.add(sqlSegment);
        this.refreshLastValue();
        return true;
    }

    /**
     * 刷新最后一个
     */
    private void refreshLastValue() {
        if (!this.isEmpty()) {
            lastValue = this.get(this.size() - 1);
        }
    }

    /**
     * 删除最后一个
     */
    private void removeLastValue() {
        this.remove(this.size() - 1);
        this.refreshLastValue();
    }

    /**
     * 清除第一个元素是 AND 或者 OR
     */
    private void flushFirstValue() {
        if (!this.isEmpty()) {
            ISqlSegment sqlSegment  = this.get(0);
            if (MatchSegment.AND_OR.match(sqlSegment)) {
                this.remove(0);
            }
        }
    }

}
