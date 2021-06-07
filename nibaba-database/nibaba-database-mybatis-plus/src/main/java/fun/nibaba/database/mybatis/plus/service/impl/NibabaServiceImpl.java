package fun.nibaba.database.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.nibaba.database.mybatis.plus.mapper.NibabaMapper;
import fun.nibaba.database.mybatis.plus.service.INibabaService;
import fun.nibaba.database.mybatis.plus.wrappers.AbstractNibabaWrapper;

import java.util.List;
import java.util.Map;

/**
 * INibabaService 实现类 Mapper Entity
 *
 * @author chenjiamin
 * @date 2021/6/7 3:57 下午
 */
public class NibabaServiceImpl<Mapper extends NibabaMapper<Entity>, Entity> extends ServiceImpl<Mapper, Entity>
        implements INibabaService<Entity> {

    @Override
    protected Class<Entity> currentMapperClass() {
        return (Class<Entity>) this.getResolvableType().as(NibabaServiceImpl.class).getGeneric(0).getType();
    }

    @Override
    protected Class<Entity> currentModelClass() {
        return (Class<Entity>) this.getResolvableType().as(NibabaServiceImpl.class).getGeneric(1).getType();
    }

    @Override
    public List<Map<String, Object>> joinList(AbstractNibabaWrapper<Entity, ?> wrapper) {
        return this.baseMapper.joinSelectList(wrapper);
    }

    @Override
    public Map<String, Object> joinOne(AbstractNibabaWrapper<Entity, ?> wrapper) {
        return this.baseMapper.joinSelect(wrapper);
    }
}
