package fun.nibaba.lazyfish.utils.converters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Boolean
 *
 * @author chenjiamin
 * @date 2022/1/20 6:05 下午
 */
public class BooleanConverter extends AbstractConverter<Boolean> {

    @Override
    public BigDecimal toBigDecimal(Boolean value) {
        throw new IllegalArgumentException("Boolean 无法转换为 BigDecimal");
    }

    @Override
    public Boolean toBoolean(Boolean value) {
        return value;
    }

    @Override
    public Double toDouble(Boolean value) {
        return value == null || !value ? 0D : 1D;
    }

    @Override
    public Float toFloat(Boolean value) {
        return value == null || !value ? 0F : 1F;
    }

    @Override
    public Integer toInteger(Boolean value) {
        return value == null || !value ? 0 : 1;
    }

    @Override
    public Long toLong(Boolean value) {
        return value == null || !value ? 0L : 1L;
    }

    @Override
    public Short toShort(Boolean value) {
        return value == null || !value ? (short) 0 : (short) 1;
    }

    @Override
    public LocalDateTime toLocalDateTime(Boolean value) {
        throw new IllegalArgumentException("Boolean 无法转换为 LocalDateTime");
    }

    @Override
    public LocalDate toLocalDate(Boolean value) {
        throw new IllegalArgumentException("Boolean 无法转换为 LocalDate");
    }

    @Override
    public LocalTime toLocalTime(Boolean value) {
        throw new IllegalArgumentException("Boolean 无法转换为 LocalTime");
    }

}
