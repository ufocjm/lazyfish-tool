package fun.nibaba.database.mybatis.plus.model;

import java.time.LocalDateTime;

/**
 * 自动填充新增信息实体
 *
 * @author chenjiamin
 * @date 2021/5/28 4:29 下午
 */
public interface FillInsertModel {

    /**
     * 设置创建人
     *
     * @param id 修改人id
     */
    void setCreateId(String id);

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    void setCreateTime(LocalDateTime createTime);

}
