package fun.nibaba.lazyfish.utils.converters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Double
 *
 * @author chenjiamin
 * @date 2022/1/20 6:11 下午
 */
public class DoubleConverter extends AbstractConverter<Double> {

    @Override
    public BigDecimal toBigDecimal(Double value) {
        if (value == null) {
            return null;
        }
        return new BigDecimal(value);
    }

    @Override
    public Boolean toBoolean(Double value) {
        return value != null && value > 0;
    }

    @Override
    public Double toDouble(Double value) {
        return value;
    }

    @Override
    public Float toFloat(Double value) {
        return value == null ? null : value.floatValue();
    }

    @Override
    public Integer toInteger(Double value) {
        return value == null ? null : value.intValue();
    }

    @Override
    public Long toLong(Double value) {
        return value == null ? null : value.longValue();
    }

    @Override
    public Short toShort(Double value) {
        return value == null ? null : value.shortValue();
    }

    @Override
    public LocalDateTime toLocalDateTime(Double value) {
        throw new IllegalArgumentException("Double 无法转换为 LocalDateTime");
    }

    @Override
    public LocalDate toLocalDate(Double value) {
        throw new IllegalArgumentException("Double 无法转换为 LocalDate");
    }

    @Override
    public LocalTime toLocalTime(Double value) {
        throw new IllegalArgumentException("Double 无法转换为 LocalTime");
    }

}
