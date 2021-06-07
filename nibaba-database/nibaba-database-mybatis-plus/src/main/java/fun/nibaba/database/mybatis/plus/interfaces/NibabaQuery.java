package fun.nibaba.database.mybatis.plus.interfaces;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * 查询字段封装
 *
 * @author chenjiamin
 * @date 2021/6/3 4:16 下午
 */
public interface NibabaQuery<MainTableClass, Child> {

    /**
     * 查询字段
     *
     * @param column 字段
     * @return 返回包装对象
     */
    Child select(SFunction<MainTableClass, ?> column);


    /**
     * 查询字段
     *
     * @param columns 字段数组
     * @return 返回包装对象
     */
    Child select(SFunction<MainTableClass, ?>... columns);

}
