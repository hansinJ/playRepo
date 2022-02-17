package com.text.demo.utils;

public class PageUtil {
    public static int adaptPageIndex(Integer pageIndex){
        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
        }
        return pageIndex;
    }

    public static int adaptPageSize(Integer pageSize){
        if (pageSize == null || pageSize <= 0) {
            pageSize = 30;
        }
        if (pageSize > 100) {
            pageSize = 100;
        }
        return pageSize;
    }
}
