package fun.nibaba.lazyfish.test.n;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UserName {

    String value();

}
