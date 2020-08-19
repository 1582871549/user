package com.meng.user.common.util;

import com.meng.user.common.base.PageResult;
import com.meng.user.common.base.Result;
import com.meng.user.common.base.ResultBase;
import com.meng.user.common.enums.ResultEnum;

import java.util.List;

public class ResultUtil {

    public static ResultBase success() {
        return new ResultBase()
                .code(ResultEnum.SUCCESS.getCode())
                .message(ResultEnum.SUCCESS.getMessage());
    }

    public static <T> ResultBase success(T data) {
        return new Result<T>()
                .data(data)
                .code(ResultEnum.SUCCESS.getCode())
                .message(ResultEnum.SUCCESS.getMessage());
    }

    public static <T> ResultBase success(List<T> data) {
        return new PageResult<T>()
                .data(data)
                .code(ResultEnum.SUCCESS.getCode())
                .message(ResultEnum.SUCCESS.getMessage());
    }

    public static ResultBase failure() {
        return new ResultBase()
                .code(ResultEnum.FAIL.getCode())
                .message(ResultEnum.FAIL.getMessage());
    }

    public static ResultBase failure(String code, String message) {
        return new ResultBase()
                .code(code)
                .message(message);
    }

    /**
     * 判断数据库操作是否成功
     *
     * @param result 数据库操作返回影响条数
     * @return boolean
     */
    public static boolean returnBool(Integer result) {
        return result != null && result > 0;
    }
}
