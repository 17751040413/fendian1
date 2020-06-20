package com.wowoniu.fendian.execption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常捕获类
 */
@RestControllerAdvice
public class CustomExtHandler {

    private static  final Logger LOG = LoggerFactory.getLogger(CustomExtHandler.class);

    @ExceptionHandler(value = Exception.class)
    Object handleException(Exception e, HttpServletRequest request ){
        Map<String,Object> map = new HashMap<>();
        map.put("code",100);
        map.put("message",e.getMessage());
        map.put("url",request.getRequestURI());
        return map;

    }
}
