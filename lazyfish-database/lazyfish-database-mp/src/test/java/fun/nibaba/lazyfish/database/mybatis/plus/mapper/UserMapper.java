package fun.nibaba.lazyfish.database.mybatis.plus.mapper;

import fun.nibaba.lazyfish.database.mybatis.plus.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author chenjiamin
 * @date 2021/5/27 4:40 下午
 */
public interface UserMapper extends LazyMapper<User> {

    List<Map<String, Object>> test(@Param("express") String express,@Param(value = "id") String id);
}
