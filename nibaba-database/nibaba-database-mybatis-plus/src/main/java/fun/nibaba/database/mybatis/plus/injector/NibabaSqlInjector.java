package fun.nibaba.database.mybatis.plus.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import fun.nibaba.database.mybatis.plus.injector.methods.JoinSelect;
import fun.nibaba.database.mybatis.plus.injector.methods.JoinSelectList;
import fun.nibaba.database.mybatis.plus.mapper.NibabaMapper;

import java.util.List;

/**
 * 抽象 SQL 默认注入器
 *
 * @author chenjiamin
 * @date 2021/5/31 4:09 下午
 */
public class NibabaSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        if (this.hasInterface(mapperClass, NibabaMapper.class)) {
            methodList.add(new JoinSelectList());
            methodList.add(new JoinSelect());
        }
        return methodList;
    }

    /**
     * 判断是否有继承的接口
     *
     * @param mapperClass    需要验证的类
     * @param interfaceClass 是否继承的接口
     * @return
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
