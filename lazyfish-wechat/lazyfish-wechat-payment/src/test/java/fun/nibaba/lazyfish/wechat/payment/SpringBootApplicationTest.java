package fun.nibaba.lazyfish.wechat.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author chenjiamin
 * @description 测试启动类
 * @date 2021/5/15 4:45 下午
 */
@ActiveProfiles(value = "junit")
@SpringBootApplication
public class SpringBootApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationTest.class);
    }

}
