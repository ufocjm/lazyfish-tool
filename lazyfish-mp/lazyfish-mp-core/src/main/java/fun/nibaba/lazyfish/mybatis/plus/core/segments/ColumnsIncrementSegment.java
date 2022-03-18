package fun.nibaba.lazyfish.mybatis.plus.core.segments;

import fun.nibaba.lazyfish.mybatis.plus.core.exceptions.LazyMybatisPlusException;

/**
 * 自增列
 *
 * @author chenjiamin
 * @date 2022-03-14 17:28:07
 */
public class ColumnsIncrementSegment implements LazySqlSegment {

    private final ColumnSegment columnSegment;

    private final String value;

    public ColumnsIncrementSegment(ColumnSegment columnSegment, String value) {
        if (value == null) {
            throw new LazyMybatisPlusException("自增自减的值不能为null");
        }
        this.columnSegment = columnSegment;
        this.value = value;
    }

    @Override
    public String getSqlSegment() {
        return this.columnSegment.getSqlSegment() + SPACE + PLUS + SPACE + this.value;
    }

}
