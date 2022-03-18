package fun.nibaba.lazyfish.mybatis.plus.core.segments;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenjiamin
 * @date 2022-03-15 11:43:50
 */
public class LazyParamMap extends HashMap<String, Object> implements Constants {

    private final AtomicInteger paramNameSeq;


    public LazyParamMap() {
        this.paramNameSeq = new AtomicInteger();
    }

    public LazyParamMap(LazyParamMap paramMap) {
       this(paramMap, paramMap.paramNameSeq);
    }

    public LazyParamMap(LazyParamMap paramMap, AtomicInteger paramNameSeq) {
        this.paramNameSeq = paramNameSeq;
        this.putAll(paramMap);
    }

    /**
     * 格式化参数,并且把取值方式放入 Map中
     *
     * @param tableNameAlia 表别名
     * @param columnCache   列缓存
     * @param value         值
     * @return 获取 paramNameValuePairs 的key值 #{eq.paramNameValuePairs.keyName}
     */
    public String formatParam(String tableNameAlia, ColumnCache columnCache, Object value) {
        String paramKey = tableNameAlia + UNDERSCORE + columnCache.getColumn() + UNDERSCORE + this.paramNameSeq.incrementAndGet();
        String paramValue = WRAPPER + WRAPPER_PARAM_MIDDLE + paramKey;
        this.put(paramKey, value);
        return SqlScriptUtils.safeParam(paramValue);
    }

}
