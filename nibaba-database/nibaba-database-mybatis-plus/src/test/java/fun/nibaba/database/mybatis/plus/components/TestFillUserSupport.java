package fun.nibaba.database.mybatis.plus.components;


/**
 * 用于mybatis-plus 自动填充用户信息的缺省类
 *
 * @author chenjiamin
 * @date 2021/5/28 4:40 下午
 */
public class TestFillUserSupport implements FillUserSupport {

    @Override
    public String getUserId() {
        return "test";
    }

}
