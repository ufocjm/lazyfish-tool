package fun.nibaba.lazyfish.mybatis.plus.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * TODO
 *
 * @author chenjiamin
 * @date 2021/5/27 4:29 下午
 */
@Data
@TableName(value = "demo_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "title")
    private String title;

    @TableField(value = "age")
    private Integer age;

    @TableField(value = "email")
    private String email;

    @TableField(value = "create_id", fill = FieldFill.INSERT_UPDATE)
    private String createId;

}
