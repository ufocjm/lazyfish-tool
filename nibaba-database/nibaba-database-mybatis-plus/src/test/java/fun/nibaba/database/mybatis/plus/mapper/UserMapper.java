package fun.nibaba.database.mybatis.plus.mapper;

import fun.nibaba.database.mybatis.plus.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author chenjiamin
 * @date 2021/5/27 4:40 下午
 */
public interface UserMapper extends NibabaMapper<User> {

    List<Map<String, Object>> test(@Param("express") String express,@Param(value = "id") String id);
}
