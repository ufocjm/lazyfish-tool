package fun.nibaba.lazyfish.utils.converters;

import fun.nibaba.lazyfish.utils.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * LocalTime
 *
 * @author chenjiamin
 * @date 2022/1/20 6:19 下午
 */
public class LocalTimeConverter extends AbstractConverter<LocalTime> {

    @Override
    public String toString(LocalTime value) {
        return DateUtils.fromLocalTime(value);
    }

    @Override
    public BigDecimal toBigDecimal(LocalTime value) {
        throw new IllegalArgumentException("LocalTime 无法转换为 BigDecimal");
    }

    @Override
    public Boolean toBoolean(LocalTime value) {
        throw new IllegalArgumentException("LocalTime 无法转换为 Boolean");
    }

    @Override
    public Double toDouble(LocalTime value) {
        throw new IllegalArgumentException("LocalTime 无法转换为 Double");
    }

    @Override
    public Float toFloat(LocalTime value) {
        throw new IllegalArgumentException("LocalTime 无法转换为 Float");
    }

    @Override
    public Integer toInteger(LocalTime value) {
        throw new IllegalArgumentException("LocalTime 无法转换为 Integer");
    }

    @Override
    public Long toLong(LocalTime value) {
        throw new IllegalArgumentException("LocalTime 无法转换为 Long");
    }

    @Override
    public Short toShort(LocalTime value) {
        throw new IllegalArgumentException("LocalTime 无法转换为 Short");
    }

    @Override
    public LocalDateTime toLocalDateTime(LocalTime value) {
        throw new IllegalArgumentException("LocalTime 无法转换为 LocalDateTime");
    }

    @Override
    public LocalDate toLocalDate(LocalTime value) {
        throw new IllegalArgumentException("LocalTime 无法转换为 LocalDate");
    }

    @Override
    public LocalTime toLocalTime(LocalTime value) {
        return value;
    }

}
