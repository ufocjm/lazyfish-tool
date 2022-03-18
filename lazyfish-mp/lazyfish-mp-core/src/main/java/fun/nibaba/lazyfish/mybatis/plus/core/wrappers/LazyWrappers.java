package fun.nibaba.lazyfish.mybatis.plus.core.wrappers;

import com.baomidou.mybatisplus.core.conditions.query.LazyLambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LazyLambdaUpdateWrapper;

/**
 * 对 mp 中的Wrappers 进行小优化
 *
 * @author chenjiamin
 * @date 2022-03-15 10:13:27
 */
public class LazyWrappers {

    public static <T> LazyLambdaQueryWrapper<T> lambdaQuery() {
        return new LazyLambdaQueryWrapper<>();
    }

    public static <T> LazyLambdaUpdateWrapper<T> lambdaUpdate() {
        return new LazyLambdaUpdateWrapper<>();
    }


}
