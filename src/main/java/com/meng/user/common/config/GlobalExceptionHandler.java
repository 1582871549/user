package com.meng.user.common.config;

import com.meng.user.common.base.ResultBase;
import com.meng.user.common.exception.BusinessException;
import com.meng.user.common.exception.RoleNotExistException;
import com.meng.user.common.util.ResultUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResultBase exception(Exception e) {

        return ResultUtil.failure();
    }

    @ExceptionHandler(BusinessException.class)
    public ResultBase exception(BusinessException e) {

        return ResultUtil.failure(e.getCode(), e.getMessage());
    }

    /**
     * 处理UserNotExistException异常
     */
    @ExceptionHandler(RoleNotExistException.class)
    public ResultBase roleNotExistException(RoleNotExistException e) {

        return ResultUtil.failure();
    }
}
