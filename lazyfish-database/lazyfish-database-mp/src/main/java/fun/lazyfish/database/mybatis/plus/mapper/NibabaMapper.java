package fun.lazyfish.database.mybatis.plus.mapper;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import fun.lazyfish.database.mybatis.plus.wrappers.AbstractNibabaWrapper;
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
        List<Map<String, Object>> results = this.joinSelectList(wrapper);
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
        Map<String, Object> result = this.joinSelect(wrapper);
        return BeanUtil.toBean(result, entityClass);
    }


    /**
     * 联表列表分页查询
     *
     * @param page    分页参数
     * @param wrapper sql构造器
     * @return 数据
     */
    <EntityClass, PageEntity extends IPage<EntityClass>> IPage<Map<String, Object>> joinSelectPage(PageEntity page, @Param(Constants.WRAPPER) AbstractNibabaWrapper<Entity, ?> wrapper);


    /**
     * 联表列表分页查询
     *
     * @param page        分页参数
     * @param wrapper     sql构造器
     * @param entityClass 转换实体
     * @return 数据
     */
    default <EntityClass, PageEntity extends IPage<EntityClass>> PageEntity joinSelectPage(PageEntity page, @Param(Constants.WRAPPER) AbstractNibabaWrapper<Entity, ?> wrapper, Class<EntityClass> entityClass) {
        IPage<Map<String, Object>> resultPage = this.joinSelectPage(page, wrapper);
        List<Map<String, Object>> records = resultPage.getRecords();
        PageEntity objectPage = (PageEntity) ReflectUtil.newInstance(page.getClass(), resultPage.getCurrent(), resultPage.getSize(), resultPage.getTotal(), resultPage.searchCount());
//        Page<EntityClass> objectPage = new Page<>(resultPage.getCurrent(), resultPage.getSize(), resultPage.getTotal(), resultPage.searchCount());
        objectPage.setRecords(BeanUtil.copyToList(records, entityClass, null));
        return objectPage;
    }


}
