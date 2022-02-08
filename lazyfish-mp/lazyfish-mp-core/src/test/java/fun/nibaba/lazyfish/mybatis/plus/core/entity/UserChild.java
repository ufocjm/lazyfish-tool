package fun.nibaba.lazyfish.mybatis.plus.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * TODO
 *
 * @author chenjiamin
 * @date 2021/5/27 4:29 下午
 */
@Data
@TableName(value = "demo_user_child")
public class UserChild {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "title")
    private String title;

    @TableField(value = "age")
    private Integer age;

    @TableField(value = "email")
    private String email;

}
