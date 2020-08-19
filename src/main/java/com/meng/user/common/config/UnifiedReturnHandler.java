package com.meng.user.common.config;

import com.google.gson.Gson;
import com.meng.user.common.base.Result;
import com.meng.user.common.base.ResultBase;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局统一返回值
 */
// @RestControllerAdvice
public class UnifiedReturnHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (body instanceof String) {
            return new Gson().toJson(new Result<>().data(body));
        }

        if (body instanceof ResultBase) {
            return body;
        }

        return new Result<>().data(body);
    }

}
