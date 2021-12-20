# Mybatis-Plus 个性化

### 简介

> 在日常工作中，很多时候使用mybatis-plus的时候都是单表操作，总有业务需求需要联表查询。经常性的需要写sql，干脆直接封装起来，使用lambda语法进行使用。



## 使用方式

```xml
<dependency>
	<groupId>fun.nibaba</groupId>
	<artifactId>lazyfish-database-mp-join</artifactId>
  <version>0.0.3-SNAPSHOT</version>
</dependency>
```





## 计划

- [x] 联表查询sql构建器
- [ ] 支持函数
- [ ] 删除对hutool工具包的依赖


## 例子

联表查询:

```java
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
                l   azyWhere.ne(UserChild::getEmail, "112");
                });
            })
            .where(lazyWhere ->
                lazyWhere.eq(User::getId, "1")
                    .and(subLazyWhere -> subLazyWhere.eq(User::getAge, "3"))
            )
            .group(lazyGroup -> lazyGroup.groupBy(User::getTitle))
            .order(lazyGroup -> lazyGroup.orderByDesc(User::getCreateId))
            .build();
        List<Map<String, Object>> list = userService.joinList(build);
```

