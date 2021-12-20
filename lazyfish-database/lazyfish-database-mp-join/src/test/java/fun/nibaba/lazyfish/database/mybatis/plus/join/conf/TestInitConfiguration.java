package fun.nibaba.lazyfish.database.mybatis.plus.join.conf;

import fun.nibaba.lazyfish.database.mybatis.plus.join.components.FillUserSupport;
import fun.nibaba.lazyfish.database.mybatis.plus.join.components.TestFillUserSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author chenjiamin
 * @date 2021/5/27 4:53 下午
 */
@Configuration
public class TestInitConfiguration {


    @Bean
    public FillUserSupport fillUserSupport() {
        return new TestFillUserSupport();
    }


}
