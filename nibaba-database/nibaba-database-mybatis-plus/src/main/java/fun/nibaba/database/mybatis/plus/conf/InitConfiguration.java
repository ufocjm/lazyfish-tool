package fun.nibaba.database.mybatis.plus.conf;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import fun.nibaba.database.mybatis.plus.components.DefaultFillUserSupport;
import fun.nibaba.database.mybatis.plus.components.FillUserSupport;
import fun.nibaba.database.mybatis.plus.components.NibabaMybatisPlusMetaObjectHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author chenjiamin
 * @date 2021/5/27 4:53 下午
 */
@Configuration
public class InitConfiguration {

    @ConditionalOnMissingBean(value = {MybatisPlusInterceptor.class, DbType.class})
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @ConditionalOnBean(DbType.class)
    @ConditionalOnMissingBean(MybatisPlusInterceptor.class)
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(DbType dbType) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(dbType));
        return interceptor;
    }

    @ConditionalOnMissingBean(FillUserSupport.class)
    @Bean
    public FillUserSupport fillUserSupport() {
        return new DefaultFillUserSupport();
    }

    @ConditionalOnBean(DefaultFillUserSupport.class)
    @ConditionalOnMissingBean(NibabaMybatisPlusMetaObjectHandler.class)
    @Bean
    public NibabaMybatisPlusMetaObjectHandler nibabaMybatisPlusMetaObjectHandler(FillUserSupport fillUserSupport) {
        return new NibabaMybatisPlusMetaObjectHandler(fillUserSupport);
    }



}
