package fun.nibaba.database.mybatis.plus.wrappers;

/**
 * Wrapper 构造
 *
 * @author chenjiamin
 * @date 2021/6/1 5:24 下午
 */
public class NibabaWrappers {

    public static <MainTableClass> NibabaJoinLambdaWrapper<MainTableClass> lambdaQuery(Class<MainTableClass> mainTableClassClass) {
        return new NibabaJoinLambdaWrapper<>(mainTableClassClass);
    }

    public static <MainTableClass> NibabaJoinLambdaWrapper<MainTableClass> lambdaQuery(Class<MainTableClass> mainTableClassClass, String tableNameAlias) {
        return new NibabaJoinLambdaWrapper<>(mainTableClassClass, tableNameAlias);
    }

}
