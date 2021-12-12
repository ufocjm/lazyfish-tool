package fun.lazyfish.database.mybatis.plus.components;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import fun.lazyfish.database.mybatis.plus.model.FillInsertModel;
import fun.lazyfish.database.mybatis.plus.model.FillUpdateModel;
import lombok.AllArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * 创建人修改人自动填充类
 *
 * @author chenjiamin
 * @date 2021/5/28 4:20 下午
 */
@AllArgsConstructor
public class NibabaMybatisPlusMetaObjectHandler implements MetaObjectHandler {

    private final FillUserSupport fillUserSupport;

    @Override
    public void insertFill(MetaObject metaObject) {
        Object originalObject = metaObject.getOriginalObject();
        if (originalObject instanceof FillInsertModel) {
            FillInsertModel fillInsertModel = (FillInsertModel) originalObject;
            fillInsertModel.setCreateId(fillUserSupport.getUserId());
            fillInsertModel.setCreateTime(LocalDateTime.now());
        }
        if (originalObject instanceof FillUpdateModel) {
            FillUpdateModel fillUpdateModel = (FillUpdateModel) originalObject;
            fillUpdateModel.setUpdateId(fillUserSupport.getUserId());
            fillUpdateModel.setUpdateTime(LocalDateTime.now());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object originalObject = metaObject.getOriginalObject();
        if (originalObject instanceof FillUpdateModel) {
            FillUpdateModel fillUpdateModel = (FillUpdateModel) originalObject;
            fillUpdateModel.setUpdateId(fillUserSupport.getUserId());
            fillUpdateModel.setUpdateTime(LocalDateTime.now());
        }
    }
}
