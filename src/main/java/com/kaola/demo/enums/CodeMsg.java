package com.kaola.demo.enums;

import lombok.Data;


public enum CodeMsg {
    SUCCESS(200,"success"),
    PWS_ERROR(300,"密码错误"),
    ACC_NOT_EXISTS(301,"账号不存在"),



    ;

    private int code;

    private String message;

    CodeMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
