package fun.nibaba.lazyfish.utils.converters;

import fun.nibaba.lazyfish.utils.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * LocalDate
 *
 * @author chenjiamin
 * @date 2022/1/20 6:19 下午
 */
public class LocalDateConverter extends AbstractConverter<LocalDate> {

    @Override
    public String toString(LocalDate value) {
        return DateUtils.fromLocalDate(value);
    }

    @Override
    public BigDecimal toBigDecimal(LocalDate value) {
        throw new IllegalArgumentException("LocalDate 无法转换为 BigDecimal");
    }

    @Override
    public Boolean toBoolean(LocalDate value) {
        throw new IllegalArgumentException("LocalDate 无法转换为 Boolean");
    }

    @Override
    public Double toDouble(LocalDate value) {
        throw new IllegalArgumentException("LocalDate 无法转换为 Double");
    }

    @Override
    public Float toFloat(LocalDate value) {
        throw new IllegalArgumentException("LocalDate 无法转换为 Float");
    }

    @Override
    public Integer toInteger(LocalDate value) {
        throw new IllegalArgumentException("LocalDate 无法转换为 Integer");
    }

    @Override
    public Long toLong(LocalDate value) {
        throw new IllegalArgumentException("LocalDate 无法转换为 Long");
    }

    @Override
    public Short toShort(LocalDate value) {
        throw new IllegalArgumentException("LocalDate 无法转换为 Short");
    }

    @Override
    public LocalDateTime toLocalDateTime(LocalDate value) {
        return value == null ? null : LocalDateTime.of(value, LocalTime.MIN);
    }

    @Override
    public LocalDate toLocalDate(LocalDate value) {
        return value;
    }

    @Override
    public LocalTime toLocalTime(LocalDate value) {
        throw new IllegalArgumentException("LocalDate 无法转换为 LocalTime");
    }

}
