package fun.nibaba.lazyfish.trans.conf;

import fun.nibaba.lazyfish.trans.component.TransFlow;
import fun.nibaba.lazyfish.trans.component.UnboxWrapperHandler;
import fun.nibaba.lazyfish.trans.helpers.TypeHelper;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 * 翻译return handler
 */
@Component
public class TransResponseBodyMethodProcessor implements HandlerMethodReturnValueHandler {

    private final RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor;

    private final UnboxWrapperHandler<Object> unboxWrapperHandler;

    private final TransFlow transFlow;

    @SuppressWarnings("unchecked")
    public TransResponseBodyMethodProcessor(RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor,
                                            UnboxWrapperHandler<?> unboxWrapperHandler,
                                            TransFlow transFlow) {
        this.requestResponseBodyMethodProcessor = requestResponseBodyMethodProcessor;
        this.unboxWrapperHandler = (UnboxWrapperHandler<Object>) unboxWrapperHandler;
        this.transFlow = transFlow;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return requestResponseBodyMethodProcessor.supportsReturnType(returnType);
    }

    /**
     * 这里主要执行1、验证 2、扫描 3、翻译操作
     *
     * @param returnValue  返回值
     * @param returnType   返回类型
     * @param mavContainer mavContainer
     * @param webRequest   webRequest
     * @throws Exception
     */
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        // 验证返回的结果是否包含需要翻译的字段
        if (returnValue != null) {
            Object unboxReturnValue = unboxWrapperHandler.unbox(returnValue);
            if (unboxReturnValue != null) {
                Class<?> trulyType = TypeHelper.getTrulyType(unboxReturnValue);
                if (!transFlow.scanned(trulyType)) {
                    transFlow.scan(trulyType);
                }
                if (transFlow.match(trulyType)) {
                    transFlow.trans(unboxReturnValue);
                }

            }
        }

        requestResponseBodyMethodProcessor.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }

}
