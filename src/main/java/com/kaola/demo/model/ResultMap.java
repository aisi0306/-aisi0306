package com.kaola.demo.model;

import com.kaola.demo.enums.CodeMsg;
import lombok.Data;

@Data
public class ResultMap<T> {

    private int code;

    private String message;

    private T data;

    public  ResultMap(){

    }

    public ResultMap(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultMap(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultMap genResultMap(CodeMsg codeMsg){
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(codeMsg.getCode());
        resultMap.setMessage(codeMsg.getMessage());
        return resultMap;
    }
    public static ResultMap genResultMap(CodeMsg codeMsg,String msg){
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(codeMsg.getCode());
        resultMap.setMessage(msg);
        return resultMap;
    }
    public static ResultMap genResultMap(CodeMsg codeMsg,Object o){
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(codeMsg.getCode());
        resultMap.setMessage(codeMsg.getMessage());
        resultMap.setData(o);
        return resultMap;
    }
}
