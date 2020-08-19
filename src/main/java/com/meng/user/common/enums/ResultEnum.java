package com.meng.user.common.enums;

public enum ResultEnum {

    FAIL("A10001", "失败"),
    SUCCESS("A10002", "成功"),
    LOGIN_FAIL("A10003","登陆失败"),
    AUTH_ERROR("A10004","认证失败"),
    USER_LOCKED("A10005","账号已被冻结"),
    USER_NOT_EXIST("A10005","账号不存在"),

    ;


    private String code;
    private String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(String code) {
        for (ResultEnum enuma : values()) {
            if (code.equals(enuma.getCode())) {
                return enuma.getMessage();
            }
        }
        return "unknown enum";
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
