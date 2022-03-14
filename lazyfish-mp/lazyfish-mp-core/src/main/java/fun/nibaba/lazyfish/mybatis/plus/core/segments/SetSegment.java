package fun.nibaba.lazyfish.mybatis.plus.core.segments;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * set sql 片段
 *
 * @author chenjiamin
 * @date 2022-03-14 16:43:09
 */
public class SetSegment extends ArrayList<ISqlSegment> implements LazySqlSegment {

    @Override
    public String getSqlSegment() {
        return this.stream().map(ISqlSegment::getSqlSegment).collect(Collectors.joining(DOT_NEWLINE));
    }
}
