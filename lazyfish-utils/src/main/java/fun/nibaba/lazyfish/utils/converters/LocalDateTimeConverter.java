package fun.nibaba.lazyfish.utils.converters;

import fun.nibaba.lazyfish.utils.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * LocalDateTime
 *
 * @author chenjiamin
 * @date 2022/1/20 6:19 下午
 */
public class LocalDateTimeConverter extends AbstractConverter<LocalDateTime> {

    @Override
    public String toString(LocalDateTime value) {
        return DateUtils.fromLocalDateTime(value);
    }

    @Override
    public BigDecimal toBigDecimal(LocalDateTime value) {
        throw new IllegalArgumentException("LocalDateTime 无法转换为 BigDecimal");
    }

    @Override
    public Boolean toBoolean(LocalDateTime value) {
        throw new IllegalArgumentException("LocalDateTime 无法转换为 Boolean");
    }

    @Override
    public Double toDouble(LocalDateTime value) {
        throw new IllegalArgumentException("LocalDateTime 无法转换为 Double");
    }

    @Override
    public Float toFloat(LocalDateTime value) {
        throw new IllegalArgumentException("LocalDateTime 无法转换为 Float");
    }

    @Override
    public Integer toInteger(LocalDateTime value) {
        throw new IllegalArgumentException("LocalDateTime 无法转换为 Integer");
    }

    @Override
    public Long toLong(LocalDateTime value) {
        throw new IllegalArgumentException("LocalDateTime 无法转换为 Long");
    }

    @Override
    public Short toShort(LocalDateTime value) {
        throw new IllegalArgumentException("LocalDateTime 无法转换为 Short");
    }

    @Override
    public LocalDateTime toLocalDateTime(LocalDateTime value) {
        return value;
    }

    @Override
    public LocalDate toLocalDate(LocalDateTime value) {
        return value == null ? null : value.toLocalDate();
    }

    @Override
    public LocalTime toLocalTime(LocalDateTime value) {
        return value == null ? null : value.toLocalTime();
    }

}
