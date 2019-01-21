package com.kaola.demo.enums;

public enum GoodType {
    SELLING(0,"未购买"),
    SELLED(1,"已购买");

    private int code;
    private String state;

    GoodType(int code, String state) {
        this.code = code;
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
