package fun.nibaba.lazyfish.trans.conf;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import fun.nibaba.lazyfish.trans.component.TransFlow;
import fun.nibaba.lazyfish.trans.component.UnboxWrapperHandler;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;

/**
 * 容器启动完成后进行初始化
 */
@AllArgsConstructor
public class InitTransRunner implements ApplicationRunner {

    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private final UnboxWrapperHandler<?> unboxWrapperHandler;

    private final TransFlow transFlow;

    /**
     * 进行初始化操作
     *
     * @param args args
     */
    @Override
    public void run(ApplicationArguments args) {
        List<HandlerMethodReturnValueHandler> handlerMethodReturnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        if (CollUtil.isEmpty(handlerMethodReturnValueHandlers)) {
            return;
        }
        List<HandlerMethodReturnValueHandler> customHandler = Lists.newArrayList();
        for (int i = 0; i < handlerMethodReturnValueHandlers.size(); i++) {
            HandlerMethodReturnValueHandler handlerMethodReturnValueHandler = handlerMethodReturnValueHandlers.get(i);
            if (handlerMethodReturnValueHandler instanceof RequestResponseBodyMethodProcessor) {
                TransResponseBodyMethodProcessor transResponseBodyMethodProcessor = new TransResponseBodyMethodProcessor((RequestResponseBodyMethodProcessor) handlerMethodReturnValueHandler, this.unboxWrapperHandler, this.transFlow);
                customHandler.add(transResponseBodyMethodProcessor);
            } else {
                customHandler.add(handlerMethodReturnValueHandler);
            }
        }
        requestMappingHandlerAdapter.setReturnValueHandlers(customHandler);
    }

}
