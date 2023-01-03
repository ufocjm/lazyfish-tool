package fun.nibaba.lazyfish.trans.conf;

import fun.nibaba.lazyfish.trans.component.UnboxWrapperHandler;
import fun.nibaba.lazyfish.trans.component.TransFlow;
import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

@AllArgsConstructor
@Component
public class TransResponseBodyMethodProcessor implements HandlerMethodReturnValueHandler {

    private final RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor;

    private final UnboxWrapperHandler unboxWrapperHandler;

    private final TransFlow transFlow;

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return requestResponseBodyMethodProcessor.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        // 验证返回的结果是否包含需要翻译的字段
        if (returnValue != null) {
            Object unboxReturnValue = unboxWrapperHandler.unbox(returnValue);
            if (unboxReturnValue != null) {
                if (!transFlow.scanned(unboxReturnValue)) {
                    transFlow.scan(unboxReturnValue);
                }
                if (transFlow.match(unboxReturnValue)) {
                    transFlow.trans(unboxReturnValue);
                }
            }
        }

        requestResponseBodyMethodProcessor.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }

}
