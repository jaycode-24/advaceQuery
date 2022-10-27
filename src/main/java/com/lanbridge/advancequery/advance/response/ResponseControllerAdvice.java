package com.lanbridge.advancequery.advance.response;

import cn.hutool.db.Page;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 返回值处理
 * @Author wangcheng
 * @Date 2022/10/26 13:55
 */
@ControllerAdvice
@ConditionalOnClass({ServerHttpRequest.class,ServerHttpResponse.class})
public class ResponseControllerAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        System.out.println("========");
        if (body instanceof PageList){
            PageList pageList = (PageList) body;
            Map<String,Object> result = new HashMap<>();
            result.put("totalCount",pageList.getTotalCount());
            result.put("totalPage",pageList.getTotalPage());
            result.put("data",pageList.getResultSetList());
            return ResponseEntity.ok().body(result);
        }
        return body;
    }
}
