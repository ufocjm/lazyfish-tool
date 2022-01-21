package fun.nibaba.lazyfish.utils.converters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Float
 *
 * @author chenjiamin
 * @date 2022/1/20 6:12 下午
 */
public class FloatConverter extends AbstractConverter<Float> {

    @Override
    public BigDecimal toBigDecimal(Float value) {
        if (value == null) {
            return null;
        }
        return new BigDecimal(value);
    }

    @Override
    public Boolean toBoolean(Float value) {
        return value != null && value > 0;
    }

    @Override
    public Double toDouble(Float value) {
        return value == null ? null : value.doubleValue();
    }

    @Override
    public Float toFloat(Float value) {
        return value;
    }

    @Override
    public Integer toInteger(Float value) {
        return value == null ? null : value.intValue();
    }

    @Override
    public Long toLong(Float value) {
        return value == null ? null : value.longValue();
    }

    @Override
    public Short toShort(Float value) {
        return value == null ? null : value.shortValue();
    }

    @Override
    public LocalDateTime toLocalDateTime(Float value) {
        throw new IllegalArgumentException("Float 无法转换为 LocalDateTime");
    }

    @Override
    public LocalDate toLocalDate(Float value) {
        throw new IllegalArgumentException("Float 无法转换为 LocalDate");
    }

    @Override
    public LocalTime toLocalTime(Float value) {
        throw new IllegalArgumentException("Float 无法转换为 LocalTime");
    }
}
