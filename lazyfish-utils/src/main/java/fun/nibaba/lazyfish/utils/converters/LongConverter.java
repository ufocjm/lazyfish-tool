package fun.nibaba.lazyfish.utils.converters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Long
 *
 * @author chenjiamin
 * @date 2022/1/20 6:16 下午
 */
public class LongConverter extends AbstractConverter<Long> {

    @Override
    public BigDecimal toBigDecimal(Long value) {
        return value == null ? null : new BigDecimal(value);
    }

    @Override
    public Boolean toBoolean(Long value) {
        return value != null && value > 0;
    }

    @Override
    public Double toDouble(Long value) {
        return value == null ? null : value.doubleValue();
    }

    @Override
    public Float toFloat(Long value) {
        return value == null ? null : value.floatValue();
    }

    @Override
    public Integer toInteger(Long value) {
        return value == null ? null : value.intValue();
    }

    @Override
    public Long toLong(Long value) {
        return value;
    }

    @Override
    public Short toShort(Long value) {
        return value == null ? null : value.shortValue();
    }

    @Override
    public LocalDateTime toLocalDateTime(Long value) {
        throw new IllegalArgumentException("Long 无法转换为 LocalDateTime");
    }

    @Override
    public LocalDate toLocalDate(Long value) {
        throw new IllegalArgumentException("Long 无法转换为 LocalDate");
    }

    @Override
    public LocalTime toLocalTime(Long value) {
        throw new IllegalArgumentException("Long 无法转换为 LocalTime");
    }

}
