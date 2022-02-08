package fun.nibaba.lazyfish.mybatis.plus.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.nibaba.lazyfish.mybatis.plus.core.mapper.LazyMapper;
import fun.nibaba.lazyfish.mybatis.plus.core.wrappers.LazyWrapper;
import fun.nibaba.lazyfish.utils.reflect.BeanUtils;

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
    default List<Map<String, Object>> lazyList(LazyWrapper wrapper) {
        return getBaseMapper().lazyList(wrapper);
    }

    /**
     * 联表列表查询
     *
     * @param wrapper     sql构造器
     * @param entityClass 转换实体
     * @return 数据
     */
    default <EntityClass> List<EntityClass> lazyList(LazyWrapper wrapper, Class<EntityClass> entityClass) {
        List<Map<String, Object>> results = lazyList(wrapper);
        return BeanUtils.mapsToBean(results, entityClass);
    }

    /**
     * 联表查询
     *
     * @param wrapper sql构造器
     * @return 数据
     */
    default Map<String, Object> lazyOne(LazyWrapper wrapper) {
        return getBaseMapper().lazyOne(wrapper);
    }

    /**
     * 联表查询
     *
     * @param wrapper     sql构造器
     * @param entityClass 转换实体
     * @return 数据
     */
    default <EntityClass> EntityClass lazyOne(LazyWrapper wrapper, Class<EntityClass> entityClass) {
        Map<String, Object> result = lazyOne(wrapper);
        return BeanUtils.mapToBean(result, entityClass);
    }

    /**
     * 联表列表分页查询
     *
     * @param page    分页参数
     * @param wrapper sql构造器
     * @return 数据
     */
    default <EntityClass, PageEntity extends IPage<EntityClass>> IPage<Map<String, Object>> lazyPage(PageEntity page, LazyWrapper wrapper) {
        return getBaseMapper().lazyPage(page, wrapper);
    }


    /**
     * 联表列表分页查询
     *
     * @param page        分页参数
     * @param wrapper     sql构造器
     * @param entityClass 转换实体
     * @return 数据
     */
    default <EntityClass, PageEntity extends IPage<EntityClass>> PageEntity lazyPage(PageEntity page, LazyWrapper wrapper, Class<EntityClass> entityClass) {
        return getBaseMapper().lazyPage(page, wrapper, entityClass);
    }

}
