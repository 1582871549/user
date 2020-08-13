package com.meng.user.common.base;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = -4995369157879021000L;

    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public Result<T> code(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> message(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }
}
