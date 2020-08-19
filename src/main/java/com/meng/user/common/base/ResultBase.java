package com.meng.user.common.base;

import java.io.Serializable;

public class ResultBase implements Serializable {

    private static final long serialVersionUID = -4995369157879021000L;
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public ResultBase code(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultBase message(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "ResultBase{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
