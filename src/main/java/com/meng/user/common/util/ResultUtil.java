package com.meng.user.common.util;

import com.meng.user.common.base.Result;
import com.meng.user.common.enums.ResultEnum;

public class ResultUtil {

    /* 1.返回成功显示的数据*/
    public static <T> Result<T> success(T t) {
        return new Result<T>()
                .code(ResultEnum.SUCCESS.getCode())
                .message(ResultEnum.SUCCESS.getMessage())
                .data(t);
    }

    public static <T> Result<T> success() {
        return new Result<T>()
                .code(ResultEnum.SUCCESS.getCode())
                .message(ResultEnum.SUCCESS.getMessage());
    }

    public static <T> Result<T> error(String code, String msg) {
        return new Result<T>()
                .code(ResultEnum.FAIL.getCode())
                .message(ResultEnum.FAIL.getMessage());
    }

}
