package fun.nibaba.lazyfish.rabbit.delay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

/**
 * 测试启动类
 *
 * @author chenjiamin
 */
@ActiveProfiles(value = "junit")
@SpringBootApplication
public class SpringBootApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationTest.class);
    }

}