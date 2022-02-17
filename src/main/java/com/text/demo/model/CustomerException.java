package com.text.demo.model;

public class CustomerException extends Exception {
    private Integer errCode;

    public CustomerException(Integer errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
    }
    public CustomerException(int errCode) {
        this.errCode = errCode;
    }

    public CustomerException(String errMsg) {
        super(errMsg);
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }
}
