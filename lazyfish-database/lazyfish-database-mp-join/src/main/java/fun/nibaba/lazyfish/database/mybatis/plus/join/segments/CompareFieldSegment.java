package fun.nibaba.lazyfish.database.mybatis.plus.join.segments;

import com.baomidou.mybatisplus.core.enums.SqlKeyword;

/**
 * 字段比较片段
 *
 * @author chenjiamin
 * @date 2021/6/4 11:59 上午
 */

public class CompareFieldSegment extends CompareSegment {

    private final ColumnSegment rightColumnSegment;

    public CompareFieldSegment(ColumnSegment leftColumnSegment, SqlKeyword sqlKeyword, ColumnSegment rightColumnSegment) {
        super(leftColumnSegment, sqlKeyword);
        this.rightColumnSegment = rightColumnSegment;
    }

    @Override
    public String getSqlSegment() {
        return super.getSqlSegment() + this.rightColumnSegment.getSqlSegment();
    }
}
