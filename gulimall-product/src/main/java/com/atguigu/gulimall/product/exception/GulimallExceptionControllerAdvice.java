package com.atguigu.gulimall.product.exception;

import com.atguigu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 集中处理素有异常
 */
//@ControllerAdvice()
@Slf4j
@RestControllerAdvice(basePackages = {"com.atguigu.gulimall.product.controller"})
public class GulimallExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handlerValidException(MethodArgumentNotValidException e){
        log.error("数据校验出现异常：{}，异常类型：{}",e.getMessage(),e.getClass());
        BindingResult result = e.getBindingResult();
        Map<String,String> map = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> {
                String field = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                map.put(field,message);
            });
        return R.error(400,"数据校验出现异常").put("data",map);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handlerValidException(Throwable throwable) {
        return R.error();
    }

}
