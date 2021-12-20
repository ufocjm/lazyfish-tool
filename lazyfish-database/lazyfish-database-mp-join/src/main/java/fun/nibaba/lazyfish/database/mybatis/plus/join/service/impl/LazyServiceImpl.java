package fun.nibaba.lazyfish.database.mybatis.plus.join.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.nibaba.lazyfish.database.mybatis.plus.join.mapper.LazyMapper;
import fun.nibaba.lazyfish.database.mybatis.plus.join.service.ILazyService;

/**
 * INibabaService 实现类 Mapper Entity
 *
 * @author chenjiamin
 * @date 2021/6/7 3:57 下午
 */
public class LazyServiceImpl<Mapper extends LazyMapper<Entity>, Entity> extends ServiceImpl<Mapper, Entity>
        implements ILazyService<Entity> {

    @Override
    protected Class<Entity> currentMapperClass() {
        return (Class<Entity>) this.getResolvableType().as(LazyServiceImpl.class).getGeneric(0).getType();
    }

    @Override
    protected Class<Entity> currentModelClass() {
        return (Class<Entity>) this.getResolvableType().as(LazyServiceImpl.class).getGeneric(1).getType();
    }

}
