package com.meng.user.common.config;

import com.meng.user.common.base.Result;
import com.meng.user.common.exception.RoleNotExistException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {

        return new Result().code("a10001").message("程序异常");
    }


    /**
     * 处理UserNotExistException异常
     */
    @ExceptionHandler(RoleNotExistException.class)
    public Result roleNotExistException(RoleNotExistException e) {

        return new Result().code("a10002").message("角色不存在");
    }
}
