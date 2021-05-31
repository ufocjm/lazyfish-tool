package fun.nibaba.database.mybatis.plus;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import fun.nibaba.database.mybatis.plus.entity.User;
import fun.nibaba.database.mybatis.plus.mapper.UserMapper;
import fun.nibaba.database.mybatis.plus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO
 *
 * @author chenjiamin
 * @date 2021/5/27 4:45 下午
 */
@SpringBootTest
public class JunitTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        System.out.println(1);
        userService.list(Wrappers.<User>lambdaQuery().eq(User::getId, "1"));
        userMapper.selectList(Wrappers.<User>lambdaQuery());
    }

}
