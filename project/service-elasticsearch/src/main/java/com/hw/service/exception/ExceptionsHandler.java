package com.hw.service.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Date: 2021/1/5
 *
 * @author WX964987
 */
@RestControllerAdvice
public class ExceptionsHandler {


    /**
     * 异常处理器
     * @param ex 其他异常
     * @return 结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String bindExceptionHandler(MethodArgumentNotValidException ex) {
        return Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
    }

    /**
     * 异常处理器
     * @param e 自定义的serviceException异常
     * @return 结果
     */
    @ExceptionHandler(ServiceException.class)
    public Map<String, Object> serviceExceptionHandler(ServiceException e) {
        String message = e.getMessage();
        int code = e.getCode();
        Map<String , Object> result = new HashMap(){{
            put("code", code);
            put("message", message);
        }};
        return result;
    }

}
