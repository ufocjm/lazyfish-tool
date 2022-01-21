package fun.nibaba.lazyfish.utils.converters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 值转换器
 *
 * @author chenjiamin
 * @date 2022/1/20 5:12 下午
 */
public interface IConverter<ValueType> {

    /**
     * 获取值类型
     *
     * @return 值类型
     */
    Class<ValueType> getTypeClass();

    /**
     * toString
     *
     * @param value value
     * @return String
     */
    String toString(ValueType value);

    /**
     * toBigDecimal
     *
     * @param value value
     * @return BigDecimal
     */
    BigDecimal toBigDecimal(ValueType value);

    /**
     * toBoolean
     *
     * @param value value
     * @return Boolean
     */
    Boolean toBoolean(ValueType value);

    /**
     * toDouble
     *
     * @param value value
     * @return Double
     */
    Double toDouble(ValueType value);

    /**
     * toFloat
     *
     * @param value value
     * @return Float
     */
    Float toFloat(ValueType value);

    /**
     * toInteger
     *
     * @param value value
     * @return Integer
     */
    Integer toInteger(ValueType value);

    /**
     * toLong
     *
     * @param value value
     * @return Long
     */
    Long toLong(ValueType value);

    /**
     * toShort
     *
     * @param value value
     * @return Short
     */
    Short toShort(ValueType value);

    /**
     * toLocalDateTime
     *
     * @param value value
     * @return LocalDateTime
     */
    LocalDateTime toLocalDateTime(ValueType value);

    /**
     * toLocalDate
     *
     * @param value value
     * @return LocalDate
     */
    LocalDate toLocalDate(ValueType value);

    /**
     * toLocalTime
     *
     * @param value value
     * @return LocalTime
     */
    LocalTime toLocalTime(ValueType value);

}
