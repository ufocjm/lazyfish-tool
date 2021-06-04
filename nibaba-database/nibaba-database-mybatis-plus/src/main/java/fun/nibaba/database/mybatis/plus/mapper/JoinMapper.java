package fun.nibaba.database.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import fun.nibaba.database.mybatis.plus.wrappers.AbstractNibabaWrapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 带有 join 查询的 Mapper
 *
 * @author chenjiamin
 * @date 2021/5/31 4:31 下午
 */
public interface JoinMapper<T> extends BaseMapper<T> {

    List<Map<String, Object>> joinSelectList(@Param(Constants.WRAPPER) AbstractNibabaWrapper wrapper);

}
