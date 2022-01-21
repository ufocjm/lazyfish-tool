package fun.nibaba.lazyfish.utils.converters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Integer
 *
 * @author chenjiamin
 * @date 2022/1/20 6:15 下午
 */
public class IntegerConverter extends AbstractConverter<Integer>{

    @Override
    public BigDecimal toBigDecimal(Integer value) {
        return value == null ? null : new BigDecimal(value);
    }

    @Override
    public Boolean toBoolean(Integer value) {
        return value != null && value > 0;
    }

    @Override
    public Double toDouble(Integer value) {
        return value == null ? null : value.doubleValue();
    }

    @Override
    public Float toFloat(Integer value) {
        return value == null ? null : value.floatValue();
    }

    @Override
    public Integer toInteger(Integer value) {
        return value;
    }

    @Override
    public Long toLong(Integer value) {
        return value == null ? null : value.longValue();
    }

    @Override
    public Short toShort(Integer value) {
        return value == null ? null : value.shortValue();
    }

    @Override
    public LocalDateTime toLocalDateTime(Integer value) {
        throw new IllegalArgumentException("Integer 无法转换为 LocalDateTime");
    }

    @Override
    public LocalDate toLocalDate(Integer value) {
        throw new IllegalArgumentException("Integer 无法转换为 LocalDate");
    }

    @Override
    public LocalTime toLocalTime(Integer value) {
        throw new IllegalArgumentException("Integer 无法转换为 LocalTime");
    }
}
