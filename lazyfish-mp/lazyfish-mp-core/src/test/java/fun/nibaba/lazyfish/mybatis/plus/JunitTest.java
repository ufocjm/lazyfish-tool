package fun.nibaba.lazyfish.mybatis.plus;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDto;
import fun.nibaba.lazyfish.mybatis.plus.entity.User;
import fun.nibaba.lazyfish.mybatis.plus.entity.UserChild;
import fun.nibaba.lazyfish.mybatis.plus.core.service.UserService;
import fun.nibaba.lazyfish.mybatis.plus.core.wrappers.LazyTable;
import fun.nibaba.lazyfish.mybatis.plus.core.wrappers.LazyWrapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    public void test() {
//        userService.list(Wrappers.<User>lambdaQuery().eq(User::getId, "1").eq(User::getName,"2"));
//        List<Map<String, Object>> maps = userMapper.joinSelectList(NibabaWrappers.lambdaQuery(User.class, "aaa"));
//        List<User> results = userService.joinList(LazyWrappers.lambdaQuery(User.class) //主表为 User.class
//                        .innerJoin(UserChild.class) //关联查询
//                        .select(UserChild::getAge)
//                        .eq(UserChild::getTitle, "2") //条件
//                        .isNull(UserChild::getAge) //条件
//                        .joinEq(UserChild::getId, User::getId) //两张表的关联字段，左边是主表，右边是关联表
//                        .joinEq(UserChild::getId, User::getId) //两张表的关联字段，左边是主表，右边是关联表 以及表别名
//                        .done() //结束标志，证明联表内部的逻辑结束(但是分组排序可以在末尾添加)
//                        .eq(User::getId, "1").and(wrapper -> {
//                            wrapper.eq(User::getAge, "1").or().eq(User::getAge, "2"); //支持嵌套语句
//                        }).like(User::getTitle, "o")
//                        .isNotNull(User::getId)
//                        .groupBy(UserChild::getAge)
//                        .orderByAsc(UserChild::getId)
//                        .orderByDesc(User::getId),
//                User.class
//        );
//
//        System.out.println(results);
//        userMapper.selectList(Wrappers.<User>lambdaQuery());
    }

    @Test
    public void test1() {
        Wrappers.<User>lambdaQuery().select(User::getId, User::getAge);
        LazyTable<User> user = new LazyTable<>(User.class, "yayaya");
        LazyTable<UserChild> userChild = new LazyTable<>(UserChild.class, "lueluelue");
        LazyWrapper build = LazyWrapper.builder(user)
                .select(lazySelect -> lazySelect.select(User::getId, User::getAge, User::getTitle).count(User::getId,"lalala"))
                .leftJoin(user, userChild, lazyJoin -> {
                    lazyJoin.select(lazySelect -> {
                        lazySelect.select(UserChild::getId, "hahahahah");
                    }).on(lazyOn -> {
                        lazyOn.eq(User::getId, UserChild::getId);
//                        lazyOn.eq(User::getAge, UserChild::getTitle);
//                        lazyOn.eq(UserChild::getEmail, "yaha");
                    }).where(lazyWhere -> {
                        lazyWhere.ne(UserChild::getEmail, "55");
                    });
                })
                .where(lazyWhere ->
                        lazyWhere.eq(User::getId, "1")
                                .and(subLazyWhere -> subLazyWhere.eq(User::getAge, "3"))
                                .in(User::getId, Lists.newArrayList("1", "2", "3"))
                                .in(User::getId, Lists.newArrayList("1"))
                                .notIn(User::getId, Lists.newArrayList( "4", "5"))
                                .notIn(User::getId, Lists.newArrayList("6"))
                )
                .group(lazyGroup -> lazyGroup.groupBy(User::getTitle).groupBy(userChild, UserChild::getId))
                .order(lazyGroup -> lazyGroup.orderByDesc(User::getCreateId).orderByDesc(userChild, UserChild::getEmail))

                .build();
        userService.lazyPage(new PageDto<>(), build, User.class);
        List<User> list = userService.lazyList(build, User.class);
        System.out.println(list);
    }


}
