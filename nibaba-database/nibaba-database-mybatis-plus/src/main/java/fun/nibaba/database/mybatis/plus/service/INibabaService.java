package fun.nibaba.database.mybatis.plus.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.nibaba.database.mybatis.plus.wrappers.AbstractNibabaWrapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 个性化 service
 *
 * @author chenjiamin
 * @date 2021/6/7 3:54 下午
 */
public interface INibabaService<Entity> extends IService<Entity> {

    /**
     * 联表列表查询
     *
     * @param wrapper
     * @return
     */
    List<Map<String, Object>> joinList(@Param(Constants.WRAPPER) AbstractNibabaWrapper<Entity, ?> wrapper);

    /**
     * 联表列表查询
     *
     * @param wrapper     sql构造器
     * @param entityClass 转换实体
     * @return 数据
     */
    default <EntityClass> List<EntityClass> joinList(@Param(Constants.WRAPPER) AbstractNibabaWrapper<Entity, ?> wrapper, Class<EntityClass> entityClass) {
        List<Map<String, Object>> results = joinList(wrapper);
        return BeanUtil.copyToList(results, entityClass, null);
    }

    /**
     * 联表查询
     *
     * @param wrapper sql构造器
     * @return 数据
     */
    Map<String, Object> joinOne(@Param(Constants.WRAPPER) AbstractNibabaWrapper<Entity, ?> wrapper);

    /**
     * 联表查询
     *
     * @param wrapper     sql构造器
     * @param entityClass 转换实体
     * @return 数据
     */
    default <EntityClass> EntityClass joinOne(@Param(Constants.WRAPPER) AbstractNibabaWrapper<Entity, ?> wrapper, Class<EntityClass> entityClass) {
        Map<String, Object> result = joinOne(wrapper);
        return BeanUtil.toBean(result, entityClass);
    }

}
