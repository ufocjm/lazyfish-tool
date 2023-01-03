package fun.nibaba.lazyfish.trans.conf;

import fun.nibaba.lazyfish.trans.component.DefaultUnboxWrapperHandler;
import fun.nibaba.lazyfish.trans.component.UnboxWrapperHandler;
import fun.nibaba.lazyfish.trans.component.TransFlow;
import fun.nibaba.lazyfish.trans.processors.TransProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;

@Configuration
public class InitConfiguration {

    @Bean
    public InitTransRunner initTransRunner(RequestMappingHandlerAdapter requestMappingHandlerAdapter,
                                           UnboxWrapperHandler unboxWrapperHandler,
                                           TransFlow transFlow) {
        return new InitTransRunner(requestMappingHandlerAdapter, unboxWrapperHandler, transFlow);
    }

    @ConditionalOnMissingBean(UnboxWrapperHandler.class)
    @Bean
    public UnboxWrapperHandler unboxWrapperHandler() {
        return new DefaultUnboxWrapperHandler();
    }

    @ConditionalOnMissingBean(TransFlow.class)
    @Bean
    public TransFlow transFlow(List<TransProcessor<?>> transProcessors) {
        return new TransFlow(transProcessors);
    }


}
