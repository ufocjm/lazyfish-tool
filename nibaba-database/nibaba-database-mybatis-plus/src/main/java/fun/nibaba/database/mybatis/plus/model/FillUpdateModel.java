package fun.nibaba.database.mybatis.plus.model;

import java.time.LocalDateTime;

/**
 * 自动填充修改信息实体
 *
 * @author chenjiamin
 * @date 2021/5/28 4:29 下午
 */
public interface FillUpdateModel extends FillInsertModel {

    /**
     * 设置修改人
     *
     * @param id 修改人id
     */

    void setUpdateId(String id);

    /**
     * 设置修改时间
     *
     * @param createTime 修改时间
     */
    void setUpdateTime(LocalDateTime createTime);


}
