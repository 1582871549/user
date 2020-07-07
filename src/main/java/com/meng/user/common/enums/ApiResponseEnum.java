package com.meng.user.common.enums;

import lombok.Getter;

/**
 * web层统一返回信息枚举
 *
 * @author dujianwei
 * @create 2020-07-06
 */
@Getter
public enum ApiResponseEnum {

    /**
     * API调用成功返回
     */
    SUCCESS(10000,"请求成功"),
    FAIL(10001,"请求失败"),
    LOGIN_FAIL(10099,"登陆失败"),
    AUTH_ERROR(10100,"认证失败");

    private int errCode = 0;

    private String errMsg;

    ApiResponseEnum(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
