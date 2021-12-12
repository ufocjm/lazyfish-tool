package fun.lazyfish.database.mybatis.plus.wrappers;

/**
 * Wrapper 构造
 *
 * @author chenjiamin
 * @date 2021/6/1 5:24 下午
 */
public class NibabaWrappers {

    /**
     * lambda 查询
     *
     * @param mainTableClassClass 实体类型
     * @param <MainTableClass>    实体类型
     * @return sql 构造器
     */
    public static <MainTableClass> NibabaJoinLambdaWrapper<MainTableClass> lambdaQuery(Class<MainTableClass> mainTableClassClass) {
        return new NibabaJoinLambdaWrapper<>(mainTableClassClass);
    }

    /**
     * lambda查询
     *
     * @param mainTableClassClass 实体类型
     * @param tableNameAlias      表别名
     * @param <MainTableClass>    实体类型
     * @return sql 构造器
     */
    public static <MainTableClass> NibabaJoinLambdaWrapper<MainTableClass> lambdaQuery(Class<MainTableClass> mainTableClassClass, String tableNameAlias) {
        return new NibabaJoinLambdaWrapper<>(mainTableClassClass, tableNameAlias);
    }

}
