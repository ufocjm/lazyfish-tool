package fun.nibaba.lazyfish.database.mybatis.plus.wrappers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * 用于涵盖 mp 自带的 lambdaQuery等方法,子类只要关心自己的实现
 *
 * @author chenjiamin
 * @date 2021/12/12 11:35 上午
 */
public class LazyWrappersAdapter {

    public static <TableClass> LambdaQueryWrapper<TableClass> lambdaQuery() {
        return Wrappers.lambdaQuery();
    }

    public static <TableClass> LambdaUpdateWrapper<TableClass> lambdaUpdate() {
        return new LambdaUpdateWrapper<>();
    }

}
