# Mybatis-Plus 个性化

### 简介

> 在日常工作中，很多时候使用mybatis-plus的时候都是单表操作，总有业务需求需要联表查询。经常性的需要写sql，干脆直接封装起来，使用lambda语法进行使用。



## 使用方式

```xml
<dependency>
	<groupId>fun.lazyfishfun.lazyfish</groupId>
	<artifactId>nibaba-database-mybatis-plus</artifactId>
  <version>0.0.2-SNAPSHOT</version>
</dependency>
```





## 计划

- [ ] 封装```联表查询```
- [ ] 租户级别的```分表分库```
- [ ] 在查询中字段以及条件使用```函数```



## 例子

联表查询:

```java
        List<User> results = userService.joinList(NibabaWrappers.lambdaQuery(User.class) //主表为 User.class
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
                    User.class
        );
```

