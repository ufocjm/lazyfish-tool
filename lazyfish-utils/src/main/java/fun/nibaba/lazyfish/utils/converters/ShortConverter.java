package fun.nibaba.lazyfish.utils.converters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Short
 *
 * @author chenjiamin
 * @date 2022/1/20 6:18 下午
 */
public class ShortConverter extends AbstractConverter<Short> {

    @Override
    public BigDecimal toBigDecimal(Short value) {
        return value == null ? null : new BigDecimal(value);
    }

    @Override
    public Boolean toBoolean(Short value) {
        return value != null && value > 0;
    }

    @Override
    public Double toDouble(Short value) {
        return value == null ? null : value.doubleValue();
    }

    @Override
    public Float toFloat(Short value) {
        return value == null ? null : value.floatValue();
    }

    @Override
    public Integer toInteger(Short value) {
        return value == null ? null : value.intValue();
    }

    @Override
    public Long toLong(Short value) {
        return value == null ? null : value.longValue();
    }

    @Override
    public Short toShort(Short value) {
        return value;
    }

    @Override
    public LocalDateTime toLocalDateTime(Short value) {
        throw new IllegalArgumentException("Short 无法转换为 LocalDateTime");
    }

    @Override
    public LocalDate toLocalDate(Short value) {
        throw new IllegalArgumentException("Short 无法转换为 LocalDate");
    }

    @Override
    public LocalTime toLocalTime(Short value) {
        throw new IllegalArgumentException("Short 无法转换为 LocalTime");
    }

}
