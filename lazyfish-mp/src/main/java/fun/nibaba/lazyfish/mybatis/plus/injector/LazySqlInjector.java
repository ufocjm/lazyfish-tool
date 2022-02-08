package fun.nibaba.lazyfish.mybatis.plus.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import fun.nibaba.lazyfish.mybatis.plus.injector.methods.LazyOne;
import fun.nibaba.lazyfish.mybatis.plus.injector.methods.LazyList;
import fun.nibaba.lazyfish.mybatis.plus.injector.methods.LazyPage;
import fun.nibaba.lazyfish.mybatis.plus.mapper.LazyMapper;

import java.util.List;

/**
 * 抽象 SQL 默认注入器
 *
 * @author chenjiamin
 * @date 2021/5/31 4:09 下午
 */
public class LazySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        if (this.hasInterface(mapperClass, LazyMapper.class)) {
            methodList.add(new LazyList());
            methodList.add(new LazyOne());
            methodList.add(new LazyPage());
        }
        return methodList;
    }

    /**
     * 判断是否有继承的接口
     *
     * @param mapperClass    需要验证的类
     * @param interfaceClass 是否继承的接口
     * @return 是否继承
     */
    private boolean hasInterface(Class<?> mapperClass, Class<?> interfaceClass) {
        Class<?>[] interfacesArray = mapperClass.getInterfaces();
        for (Class<?> item : interfacesArray) {
            if (item == interfaceClass) {
                return true;
            }
        }
        return false;
    }

}
