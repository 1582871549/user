package com.meng.user.common.exception;

import com.meng.user.common.enums.ResultEnum;

/**
 * 全局异常处理类
 * 处理所有业务异常
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 610381362157914886L;
    private String code;

    public BusinessException() {
        super(ResultEnum.FAIL.getMessage());
        this.code = ResultEnum.FAIL.getCode();
    }

    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public BusinessException(Throwable cause) {
        super(ResultEnum.FAIL.getMessage(), cause);
        this.code = ResultEnum.FAIL.getCode();
    }

    public BusinessException(ResultEnum resultEnum, Throwable cause) {
        super(resultEnum.getMessage(), cause);
        this.code = resultEnum.getCode();
    }

    public String getCode() {
        return code;
    }
}
