package fun.nibaba.lazyfish.mybatis.plus.join.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.nibaba.lazyfish.mybatis.plus.join.mapper.LazyMapper;
import fun.nibaba.lazyfish.mybatis.plus.join.wrappers.LazyWrapper;

import java.util.List;
import java.util.Map;

/**
 * 个性化 service
 *
 * @author chenjiamin
 * @date 2021/6/7 3:54 下午
 */
public interface ILazyService<Entity> extends IService<Entity> {

    /**
     * 获取mapper
     *
     * @return mapper
     */
    @Override
    LazyMapper<Entity> getBaseMapper();

    /**
     * 联表列表查询
     *
     * @param wrapper
     * @return
     */
    default List<Map<String, Object>> joinList(LazyWrapper wrapper) {
        return getBaseMapper().joinSelectList(wrapper);
    }

    /**
     * 联表列表查询
     *
     * @param wrapper     sql构造器
     * @param entityClass 转换实体
     * @return 数据
     */
    default <EntityClass> List<EntityClass> joinList(LazyWrapper wrapper, Class<EntityClass> entityClass) {
        List<Map<String, Object>> results = joinList(wrapper);
        return BeanUtil.copyToList(results, entityClass, null);
    }

    /**
     * 联表查询
     *
     * @param wrapper sql构造器
     * @return 数据
     */
    default Map<String, Object> joinOne(LazyWrapper wrapper) {
        return getBaseMapper().joinSelect(wrapper);
    }

    /**
     * 联表查询
     *
     * @param wrapper     sql构造器
     * @param entityClass 转换实体
     * @return 数据
     */
    default <EntityClass> EntityClass joinOne(LazyWrapper wrapper, Class<EntityClass> entityClass) {
        Map<String, Object> result = joinOne(wrapper);
        return BeanUtil.toBean(result, entityClass);
    }

    /**
     * 联表列表分页查询
     *
     * @param page    分页参数
     * @param wrapper sql构造器
     * @return 数据
     */
    default <EntityClass, PageEntity extends IPage<EntityClass>> IPage<Map<String, Object>> joinPage(PageEntity page, LazyWrapper wrapper) {
        return getBaseMapper().joinSelectPage(page, wrapper);
    }


    /**
     * 联表列表分页查询
     *
     * @param page        分页参数
     * @param wrapper     sql构造器
     * @param entityClass 转换实体
     * @return 数据
     */
    default <EntityClass, PageEntity extends IPage<EntityClass>> PageEntity joinPage(PageEntity page, LazyWrapper wrapper, Class<EntityClass> entityClass) {
        return getBaseMapper().joinSelectPage(page, wrapper, entityClass);
    }

}
