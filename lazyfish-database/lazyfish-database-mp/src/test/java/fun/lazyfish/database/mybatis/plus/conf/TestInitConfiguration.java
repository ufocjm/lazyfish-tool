package fun.lazyfish.database.mybatis.plus.conf;

import fun.lazyfish.database.mybatis.plus.components.FillUserSupport;
import fun.lazyfish.database.mybatis.plus.components.TestFillUserSupport;
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
