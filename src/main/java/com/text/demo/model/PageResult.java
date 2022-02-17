package com.text.demo.model;

import java.io.Serializable;

public class PageResult<T> implements Serializable {
    private long total;
    private int pageIndex;
    private int pageSize;
    private T rows;

    public long getTotal() {
        return total;
    }

    public PageResult setTotal(long total) {
        this.total = total;
        return this;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public PageResult setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageResult setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public T getRows() {
        return rows;
    }

    public PageResult setRows(T rows) {
        this.rows = rows;
        return this;
    }
}