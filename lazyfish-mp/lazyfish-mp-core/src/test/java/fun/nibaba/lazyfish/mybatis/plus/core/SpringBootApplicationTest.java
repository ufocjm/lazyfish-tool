package fun.nibaba.lazyfish.mybatis.plus.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

/**
 * 测试启动类
 *
 * @author chenjiamin
 */
@MapperScan(value = "fun.nibaba.lazyfish.mybatis.plus.core.mapper")
@ActiveProfiles(value = "junit")
@SpringBootApplication
public class SpringBootApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationTest.class);
    }

}