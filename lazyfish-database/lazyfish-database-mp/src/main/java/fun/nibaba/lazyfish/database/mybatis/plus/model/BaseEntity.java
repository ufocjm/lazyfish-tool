package fun.nibaba.lazyfish.database.mybatis.plus.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 基础实体
 *
 * @author chenjiamin
 * @date 2021/5/28 4:52 下午
 */
@Getter
@Setter
public class BaseEntity<T extends BaseEntity<T>> extends Model<T> implements FillUpdateModel {

    /**
     * 创建人
     */
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private String createId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_id", fill = FieldFill.INSERT_UPDATE)
    private String updateId;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
