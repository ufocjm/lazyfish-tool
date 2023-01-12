package fun.nibaba.lazyfish.trans.processors;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 注解扫描处理器
 *
 * @param <A>
 */
public abstract class AbstractAnnotationTransScanProcessor<A extends Annotation> implements TransScanProcessor<A> {

    /**
     * 注解类型
     */
    private final Class<A> annotationType;

    /**
     * 默认构造器<br/>
     * 默认获取泛型的类型
     */
    @SuppressWarnings("unchecked")
    public AbstractAnnotationTransScanProcessor() {
        this.annotationType = (Class<A>) ClassUtil.getTypeArgument(this.getClass(), this.getTypeArgumentIndex());
    }

    @Override
    public boolean match(Field field) {
        return AnnotationUtil.hasAnnotation(field, this.getClassType());
    }

    @Override
    public Class<A> getClassType() {
        return this.annotationType;
    }

    /**
     * 获取泛型的位数
     *
     * @return 0
     */
    protected int getTypeArgumentIndex() {
        return 0;
    }

}
