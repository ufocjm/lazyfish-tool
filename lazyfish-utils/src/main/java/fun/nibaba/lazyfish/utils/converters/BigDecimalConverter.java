package fun.nibaba.lazyfish.utils.converters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * BigDecimal
 *
 * @author chenjiamin
 * @date 2022/1/20 6:02 下午
 */
public class BigDecimalConverter extends AbstractConverter<BigDecimal> {

    @Override
    public BigDecimal toBigDecimal(BigDecimal value) {
        return value;
    }

    @Override
    public Boolean toBoolean(BigDecimal value) {
        if (value == null) {
            return false;
        }
        return value.compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public Double toDouble(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return value.doubleValue();
    }

    @Override
    public Float toFloat(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return value.floatValue();
    }

    @Override
    public Integer toInteger(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return value.intValue();
    }

    @Override
    public Long toLong(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return value.longValue();
    }

    @Override
    public Short toShort(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return value.shortValue();
    }

    @Override
    public LocalDateTime toLocalDateTime(BigDecimal value) {
        throw new IllegalArgumentException("BigDecimal 无法转换为 LocalDateTime");
    }

    @Override
    public LocalDate toLocalDate(BigDecimal value) {
        throw new IllegalArgumentException("BigDecimal 无法转换为 LocalDate");
    }

    @Override
    public LocalTime toLocalTime(BigDecimal value) {
        throw new IllegalArgumentException("BigDecimal 无法转换为 LocalTime");
    }
}
