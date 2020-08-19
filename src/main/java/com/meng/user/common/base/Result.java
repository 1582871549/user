package com.meng.user.common.base;

public class Result<T> extends ResultBase {

    private static final long serialVersionUID = 2613006242860927638L;
    private T data;

    public T getData() {
        return data;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                "} " + super.toString();
    }
}
