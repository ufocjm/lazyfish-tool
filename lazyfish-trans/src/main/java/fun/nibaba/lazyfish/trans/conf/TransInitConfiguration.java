package fun.nibaba.lazyfish.trans.conf;

import fun.nibaba.lazyfish.trans.component.DefaultUnboxWrapperHandler;
import fun.nibaba.lazyfish.trans.component.TransFlow;
import fun.nibaba.lazyfish.trans.component.UnboxWrapperHandler;
import fun.nibaba.lazyfish.trans.processors.TransScanProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;

/**
 * 初始化
 */
@Configuration
public class TransInitConfiguration {


    /**
     * 默认翻译流程
     *
     * @param transScanProcessors 扫描处理器
     * @return bean
     */
    @ConditionalOnMissingBean(TransFlow.class)
    @Bean
    public TransFlow transFlow(List<TransScanProcessor<?>> transScanProcessors) {
        return new TransFlow(transScanProcessors);
    }

    /**
     * 默认拆包
     *
     * @return bean
     */
    @ConditionalOnMissingBean(UnboxWrapperHandler.class)
    @Bean
    public UnboxWrapperHandler<?> unboxWrapperHandler() {
        return new DefaultUnboxWrapperHandler();
    }

    /**
     * 初始化
     *
     * @param requestMappingHandlerAdapter requestMappingHandlerAdapter
     * @param unboxWrapperHandler          拆箱handler
     * @param transFlow                    默认翻译流程
     * @return bean
     */
    @Bean
    public InitTransRunner initTransRunner(RequestMappingHandlerAdapter requestMappingHandlerAdapter,
                                           UnboxWrapperHandler<?> unboxWrapperHandler,
                                           TransFlow transFlow) {
        return new InitTransRunner(requestMappingHandlerAdapter, unboxWrapperHandler, transFlow);
    }

}
