package com.meng.user.common.base;

import java.util.List;

public class PageResult<T> extends ResultBase {

    private static final long serialVersionUID = 2613006242860927638L;
    private int total;
    private List<T> data;


    public List<T> getData() {
        return data;
    }

    public PageResult<T> data(List<T> data) {
        this.data = data;
        this.total = data.size();
        return this;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", data=" + data +
                "} " + super.toString();
    }
}
