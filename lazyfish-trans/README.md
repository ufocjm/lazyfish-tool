# 自动翻译

- [x] 注解自动翻译

- [ ] 接口自动翻译

## 如何初始化

- 方式1:

  1. 在项目`resources`下创建`META-INF文`件夹

  2. 然后创建`spring.factories`文件

     ```properties
     org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
       fun.nibaba.lazyfish.trans.conf.TransInitConfiguration
     ```

- 方式2:

  1. 通过`@Configuration`注解的方式进行初始化

     ```java
     /**
      * 初始化
      */
     @Configuration
     public class InitConfiguration {
     
     
         /**
          * 声明Bean
          *
          * @param transScanProcessors 扫描处理器
          * @return bean
          */
         @Bean
         public TransInitConfiguration transInitConfiguration() {
             return new TransInitConfiguration();
         }
     
     }
     ```


## 注解自动翻译

示例代码：

```java
package fun.nibaba.lazyfish.test.test;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import fun.nibaba.lazyfish.trans.processors.AbstractAnnotationTransScanProcessor;
import fun.nibaba.lazyfish.trans.helpers.TransMethod;
import fun.nibaba.lazyfish.trans.helpers.TransMethodHelper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserNameTransScanProcessor extends AbstractAnnotationTransScanProcessor<UserName> {

    @Override
    public TransMethod getTransMethod(Field field) {
        String fieldName = field.getName();
        String getterName = StrUtil.genGetter(fieldName);
        Method getterMethod = ReflectUtil.getMethod(field.getDeclaringClass(), getterName);
        if (getterMethod == null) {
            getterMethod = TransMethodHelper.generalGetter(field);
        }

        UserName annotation = AnnotationUtil.getAnnotation(field, this.getClassType());
        // 尝试获取method
        String value = annotation.value();
        Field targetField = ReflectUtil.getField(field.getDeclaringClass(), value);
        Method setterMethod;
        if (targetField == null) {
            setterMethod = ReflectUtil.getMethod(field.getDeclaringClass(), StrUtil.genSetter(value), String.class);
        } else {
            setterMethod = TransMethodHelper.generalSetter(targetField);
        }
        return new TransMethod(field, getterMethod, setterMethod);
    }

    /**
     * 获取字典
     *
     * @param keys keys
     * @return 字典
     */
    @Override
    public Map<Object, Object> getTransMap(List<Object> keys) {
        return keys.stream().collect(Collectors.toMap(Function.identity(), o -> o + "名字"));
    }

}

```

## 自动拆箱

> 如果返回全部都有包装类包裹的情况下，就要声明自己的拆箱方式

示例代码：

```java
package fun.nibaba.lazyfish.test.test;

import fun.nibaba.lazyfish.test.controller.R;
import fun.nibaba.lazyfish.trans.component.UnboxWrapperHandler;
import org.springframework.stereotype.Component;

@Component
public class ResultUnboxWrapperHandler implements UnboxWrapperHandler<R<?>> {
    /**
     * 拆箱
     *
     * @param returnValue 值
     * @return 拆箱后的值
     */
    @Override
    public Object unbox(R<?> returnValue) {
        return returnValue.getData();
    }
}
```

