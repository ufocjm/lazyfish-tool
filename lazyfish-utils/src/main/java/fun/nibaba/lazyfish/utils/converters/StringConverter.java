package fun.nibaba.lazyfish.utils.converters;

import fun.nibaba.lazyfish.utils.DateUtils;
import fun.nibaba.lazyfish.utils.StringConstants;
import fun.nibaba.lazyfish.utils.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 字符串转换器
 *
 * @author chenjiamin
 * @date 2022/1/20 5:31 下午
 */
public class StringConverter extends AbstractConverter<String> {

    @Override
    public BigDecimal toBigDecimal(String value) {
        if (value == null) {
            return null;
        }
        if (StringConstants.EMPTY.equals(value)) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(value);
    }

    @Override
    public Boolean toBoolean(String value) {
        if (StringConstants.TRUE.equals(value) || StringConstants.ONE.equals(value)) {
            return true;
        }
        return false;
    }

    @Override
    public Double toDouble(String value) {
        if (value == null) {
            return null;
        }
        if (StringConstants.EMPTY.equals(value)) {
            return 0D;
        }
        if (!Validator.isNumber(value)) {
            return 0D;
        }
        return Double.parseDouble(value);
    }

    @Override
    public Float toFloat(String value) {
        if (value == null) {
            return null;
        }
        if (StringConstants.EMPTY.equals(value)) {
            return 0F;
        }
        if (!Validator.isNumber(value)) {
            return 0F;
        }
        return Float.parseFloat(value);
    }

    @Override
    public Integer toInteger(String value) {
        if (value == null) {
            return null;
        }
        if (StringConstants.EMPTY.equals(value)) {
            return 0;
        }
        if (!Validator.isNumber(value)) {
            return 0;
        }
        return Integer.parseInt(value);
    }

    @Override
    public Long toLong(String value) {
        if (value == null) {
            return null;
        }
        if (StringConstants.EMPTY.equals(value)) {
            return 0L;
        }
        if (!Validator.isNumber(value)) {
            return 0L;
        }
        return Long.parseLong(value);
    }

    @Override
    public Short toShort(String value) {
        if (value == null) {
            return null;
        }
        if (StringConstants.EMPTY.equals(value)) {
            return 0;
        }
        if (!Validator.isNumber(value)) {
            return 0;
        }
        return Short.parseShort(value);
    }

    @Override
    public LocalDateTime toLocalDateTime(String value) {
        return DateUtils.toLocalDateTime(value);
    }

    @Override
    public LocalDate toLocalDate(String value) {
        return DateUtils.toLocalDate(value);
    }

    @Override
    public LocalTime toLocalTime(String value) {
        return DateUtils.toLocalTime(value);
    }

}
