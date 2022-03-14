package fun.nibaba.lazyfish.mybatis.plus.core.segments;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import fun.nibaba.lazyfish.mybatis.plus.core.exceptions.LazyMybatisPlusException;
import fun.nibaba.lazyfish.utils.StrUtils;

/**
 * 值比较片段
 *
 * @author chenjiamin
 * @date 2021/6/3 10:48 上午
 */
public class CompareValueSegment extends CompareSegment {

    protected final ISqlSegment paramValueKey;

    public CompareValueSegment(ColumnSegment columnSegment, SqlKeyword sqlKeyword, String paramValueKey) {
        this(columnSegment, sqlKeyword, () -> paramValueKey);
    }

    public CompareValueSegment(ColumnSegment columnSegment, SqlKeyword sqlKeyword, ISqlSegment paramValueKey) {
        super(columnSegment, sqlKeyword);
        if (StrUtils.isBlank(paramValueKey.getSqlSegment())) {
            throw new LazyMybatisPlusException("sql片段键值对关键字不能为空");
        }
        this.paramValueKey = paramValueKey;
    }


    @Override
    public String getSqlSegment() {
        String sqlSegment = super.getSqlSegment();
        return sqlSegment + paramValueKey.getSqlSegment();
    }
}
