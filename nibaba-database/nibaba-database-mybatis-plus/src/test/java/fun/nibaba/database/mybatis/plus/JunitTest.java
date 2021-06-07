package fun.nibaba.database.mybatis.plus;

import fun.nibaba.database.mybatis.plus.entity.User;
import fun.nibaba.database.mybatis.plus.entity.UserChild;
import fun.nibaba.database.mybatis.plus.mapper.UserMapper;
import fun.nibaba.database.mybatis.plus.service.UserService;
import fun.nibaba.database.mybatis.plus.wrappers.NibabaWrappers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

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
        List<HashMap> results = userService.joinList(NibabaWrappers.lambdaQuery(User.class) //主表为 User.class
                        .innerJoin(UserChild.class) //关联查询
                        .eq(UserChild::getName, "2") //条件
                        .isNull(UserChild::getAge) //条件
                        .joinEq(UserChild::getId, User::getId) //两张表的关联字段，左边是主表，右边是关联表
                        .joinEq(UserChild::getId, User::getId, "tableNameAlias") //两张表的关联字段，左边是主表，右边是关联表 以及表别名
                        .done() //结束标志，证明联表内部的逻辑结束(但是分组排序可以在末尾添加)
                        .eq(User::getId, "1").and(wrapper -> {
                            wrapper.eq(User::getAge, "1").or().eq(User::getAge, "2"); //支持嵌套语句
                        }).like(User::getName, "o")
                        .isNotNull(User::getId)
                        .groupBy(UserChild::getAge)
                        .orderByAsc(UserChild::getId)
                        .orderByDesc(User::getId),
                HashMap.class
        );

        System.out.println(1);
//        userMapper.selectList(Wrappers.<User>lambdaQuery());
    }

}
