package fun.nibaba.database.mybatis.plus.mapper;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import fun.nibaba.database.mybatis.plus.wrappers.AbstractNibabaWrapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 带有 join 查询的 Mapper
 *
 * @author chenjiamin
 * @date 2021/5/31 4:31 下午
 */
public interface NibabaMapper<Entity> extends BaseMapper<Entity> {

    /**
     * 联表列表查询
     *
     * @param wrapper sql构造器
     * @return 数据
     */
    List<Map<String, Object>> joinSelectList(@Param(Constants.WRAPPER) AbstractNibabaWrapper<Entity, ?> wrapper);


    /**
     * 联表列表查询
     *
     * @param wrapper     sql构造器
     * @param entityClass 转换实体
     * @return 数据
     */
    default <EntityClass> List<EntityClass> joinSelectList(@Param(Constants.WRAPPER) AbstractNibabaWrapper<Entity, ?> wrapper, Class<EntityClass> entityClass) {
        List<Map<String, Object>> results = joinSelectList(wrapper);
        return BeanUtil.copyToList(results, entityClass, null);
    }

    /**
     * 联表查询
     *
     * @param wrapper sql构造器
     * @return 数据
     */
    Map<String, Object> joinSelect(@Param(Constants.WRAPPER) AbstractNibabaWrapper<?, ?> wrapper);

    /**
     * 联表查询
     *
     * @param wrapper     sql构造器
     * @param entityClass 转换实体
     * @return 数据
     */
    default <EntityClass> EntityClass joinSelect(@Param(Constants.WRAPPER) AbstractNibabaWrapper<?, ?> wrapper, Class<EntityClass> entityClass) {
        Map<String, Object> result = joinSelect(wrapper);
        return BeanUtil.toBean(result, entityClass);
    }

}
