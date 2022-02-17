package com.text.demo.model;

public class ApiResult<T> {
    public ApiResult() {

    }

    public ApiResult(int code) {
        this.setCode(code);
    }

    public ApiResult(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public ApiResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ApiResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}