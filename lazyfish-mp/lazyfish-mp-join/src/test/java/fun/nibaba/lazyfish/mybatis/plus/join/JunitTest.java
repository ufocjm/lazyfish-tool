package fun.nibaba.lazyfish.mybatis.plus.join;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import fun.nibaba.lazyfish.mybatis.plus.entity.User;
import fun.nibaba.lazyfish.mybatis.plus.entity.UserChild;
import fun.nibaba.lazyfish.mybatis.plus.join.service.UserService;
import fun.nibaba.lazyfish.mybatis.plus.join.wrappers.LazyTable;
import fun.nibaba.lazyfish.mybatis.plus.join.wrappers.LazyWrapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

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


        LazyTable<User> user = new LazyTable<>(User.class);
        LazyTable<UserChild> userChild = new LazyTable<>(UserChild.class);
        LazyWrapper build = LazyWrapper.builder(user)
                .select(lazySelect -> lazySelect.select(User::getId, User::getAge, User::getTitle))
                .leftJoin(user, userChild, lazyJoin -> {
                    lazyJoin.select(lazySelect -> {
                        lazySelect.select(UserChild::getId);
                    }).on(lazyOn -> {
                        lazyOn.eq(User::getAge, UserChild::getTitle);
                    }).where(lazyWhere -> {
                        lazyWhere.ne(UserChild::getEmail, "112");
                    });
                })
                .where(lazyWhere ->
                        lazyWhere.eq(User::getId, "1")
                                .and(subLazyWhere -> subLazyWhere.eq(User::getAge, "3"))
                                .in(User::getId, Lists.newArrayList("1", "2", "3"))
                )
                .group(lazyGroup -> lazyGroup.groupBy(User::getTitle).groupBy(userChild, UserChild::getId))
                .order(lazyGroup -> lazyGroup.orderByDesc(User::getCreateId).orderByDesc(userChild, UserChild::getEmail))

                .build();
        List<Map<String, Object>> list = userService.joinList(build);
        System.out.println(list);
//        System.out.println("SELECT ");
//        System.out.println(build.getSqlSelect());
//        System.out.println("demo_user ");
//        System.out.println("AS ");
//        System.out.println(build.getTableNameAlias());
//        System.out.println(" FROM ");
//        List<LazyJoinWrapper> joinSegmentList = build.getJoinSegmentList();
//        for (LazyJoinWrapper lazyJoinWrapper : joinSegmentList) {
//            System.out.println(lazyJoinWrapper.getJoinType());
//            System.out.println(lazyJoinWrapper.getTableName());
//            System.out.println(lazyJoinWrapper.getWhereSegment());
//        }
//        System.out.println(build.getWhereSegment());
//        System.out.println(build.getGroupBySegment());
//        System.out.println(build.getOrderBySegment());
//        System.out.println(build.getLastSql());
    }

}
