package fun.nibaba.lazyfish.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期相关工具
 *
 * @author chenjiamin
 * @date 2022/1/20 5:48 下午
 */
public class DateUtils {

    public final static String YYYY_MM_DD_HH_MM_SS_STR = "yyyy-MM-dd HH:mm:ss";

    public final static DateTimeFormatter YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_STR);

    public final static String YYYY_MM_DD_STR = "yyyy-MM-dd";

    public final static DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern(YYYY_MM_DD_STR);

    public final static String HH_MM_SS_STR = "HH:mm:ss";

    public final static DateTimeFormatter HH_MM_SS = DateTimeFormatter.ofPattern(HH_MM_SS_STR);

    /**
     * toLocalDateTime
     *
     * @param dateStr dateStr
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String dateStr) {
        return toLocalDateTime(dateStr, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * toLocalDateTime
     *
     * @param dateStr dateStr
     * @param df      DateTimeFormatter
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String dateStr, DateTimeFormatter df) {
        if (StrUtils.isNotEmpty(dateStr) && dateStr.length() == YYYY_MM_DD_HH_MM_SS_STR.length()) {
            return LocalDateTime.parse(dateStr, df);
        }
        return null;
    }

    /**
     * fromLocalDateTime
     *
     * @param localDateTime LocalDateTime
     * @return dateStr
     */
    public static String fromLocalDateTime(LocalDateTime localDateTime) {
        return fromLocalDateTime(localDateTime, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * fromLocalDateTime
     *
     * @param localDateTime LocalDateTime
     * @param df            DateTimeFormatter
     * @return dateStr
     */
    public static String fromLocalDateTime(LocalDateTime localDateTime, DateTimeFormatter df) {
        return df.format(localDateTime);
    }

    /**
     * toLocalDate
     *
     * @param dateStr dateStr
     * @return LocalDate
     */
    public static LocalDate toLocalDate(String dateStr) {
        return toLocalDate(dateStr, YYYY_MM_DD);
    }

    /**
     * toLocalDate
     *
     * @param dateStr dateStr
     * @param df      DateTimeFormatter
     * @return LocalDate
     */
    public static LocalDate toLocalDate(String dateStr, DateTimeFormatter df) {
        if (StrUtils.isNotEmpty(dateStr) && dateStr.length() == YYYY_MM_DD_STR.length()) {
            return LocalDate.parse(dateStr, df);
        }
        return null;
    }

    /**
     * fromLocalDate
     *
     * @param localDate LocalDate
     * @return dateStr
     */
    public static String fromLocalDate(LocalDate localDate) {
        return fromLocalDate(localDate, YYYY_MM_DD);
    }

    /**
     * fromLocalDate
     *
     * @param localDate LocalDate
     * @param df        DateTimeFormatter
     * @return dateStr
     */
    public static String fromLocalDate(LocalDate localDate, DateTimeFormatter df) {
        return df.format(localDate);
    }

    /**
     * toLocalTime
     *
     * @param dateStr dateStr
     * @return LocalTime
     */
    public static LocalTime toLocalTime(String dateStr) {
        return toLocalTime(dateStr, YYYY_MM_DD);
    }

    /**
     * toLocalTime
     *
     * @param dateStr dateStr
     * @param df      DateTimeFormatter
     * @return LocalTime
     */
    public static LocalTime toLocalTime(String dateStr, DateTimeFormatter df) {
        if (StrUtils.isNotEmpty(dateStr) && dateStr.length() == HH_MM_SS_STR.length()) {
            return LocalTime.parse(dateStr, df);
        }
        return null;
    }

    /**
     * fromLocalTime
     *
     * @param localTime LocalTime
     * @return dateStr
     */
    public static String fromLocalTime(LocalTime localTime) {
        return fromLocalTime(localTime, HH_MM_SS);
    }

    /**
     * fromLocalTime
     *
     * @param localTime LocalTime
     * @param df        DateTimeFormatter
     * @return dateStr
     */
    public static String fromLocalTime(LocalTime localTime, DateTimeFormatter df) {
        return df.format(localTime);
    }

}
