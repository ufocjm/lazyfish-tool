package fun.nibaba.lazyfish.wechat.payment.utils;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * java 8 时间工具类
 *
 * @author chenjiamin
 * @date 2021/5/18 10:52 上午
 */
@UtilityClass
public class LocalDateTimeUtil {

    public final static DateTimeFormatter LOCAL_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public final static DateTimeFormatter PURE_DATETIME_MS = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public final static DateTimeFormatter PURE_DATETIME = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * 当前时间戳 秒
     *
     * @return
     */
    public long currentTime() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 当前时间戳 毫秒
     *
     * @return
     */
    public long currentTimeStamp() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public String getNowPureDatetime() {
        return formatLocalDateTime(LocalDateTime.now(), PURE_DATETIME);
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public String getNowPureDatetimeMs() {
        return formatLocalDateTime(LocalDateTime.now(), PURE_DATETIME_MS);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public String getNowLocalDateTime() {
        return formatLocalDateTime(LocalDateTime.now());
    }

    /**
     * 时间转换
     *
     * @param localDateTime
     * @return
     */
    public LocalDateTime parseLocalDateTime(String localDateTime) {
        if (StrUtil.isBlank(localDateTime)) {
            throw new NullPointerException("日期不能为空");
        }
        return LocalDateTime.parse(localDateTime, LOCAL_DATE_TIME);
    }

    /**
     * 时间转换
     *
     * @param localDateTime
     * @return
     */
    public LocalDateTime parsePureLocalDateTime(String localDateTime) {
        if (StrUtil.isBlank(localDateTime)) {
            throw new NullPointerException("日期不能为空");
        }
        return LocalDateTime.parse(localDateTime, PURE_DATETIME);
    }

    /**
     * 时间转换
     *
     * @param localDateTime
     * @return
     */
    public String formatLocalDateTime(LocalDateTime localDateTime) {
        return formatLocalDateTime(localDateTime, LOCAL_DATE_TIME);
    }

    /**
     * 时间转换
     *
     * @param localDateTime
     * @return
     */
    public String formatLocalDateTime(LocalDateTime localDateTime, DateTimeFormatter df) {
        return df.format(localDateTime);
    }

}
