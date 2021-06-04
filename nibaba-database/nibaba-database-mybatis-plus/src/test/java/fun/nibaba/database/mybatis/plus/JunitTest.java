package fun.nibaba.database.mybatis.plus;

import fun.nibaba.database.mybatis.plus.entity.User;
import fun.nibaba.database.mybatis.plus.entity.UserChild;
import fun.nibaba.database.mybatis.plus.mapper.UserMapper;
import fun.nibaba.database.mybatis.plus.service.UserService;
import fun.nibaba.database.mybatis.plus.wrappers.NibabaWrappers;
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
//        userService.list(Wrappers.<User>lambdaQuery().eq(User::getId, "1").eq(User::getName,"2"));
//        List<Map<String, Object>> maps = userMapper.joinSelectList(NibabaWrappers.lambdaQuery(User.class, "aaa"));
        userMapper.joinSelectList(NibabaWrappers.lambdaQuery(User.class)
                .innerJoin(UserChild.class)
                .eq(UserChild::getName, "2")
                .joinEq(UserChild::getId, User::getId)
                .done()
                .eq(User::getId, "1").and(wrapper -> {
                    wrapper.eq(User::getAge, "1").or().eq(User::getAge, "2");
                }).like(User::getName, "o"));

        System.out.println(1);
//        userMapper.selectList(Wrappers.<User>lambdaQuery());
    }

}
