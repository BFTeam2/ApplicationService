package com.example.transactionmanagementdemo.common.domain;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ResultDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    private Object data;

    public ResultDto(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultDto(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultDto success(Object data){
        return new ResultDto(HttpStatus.OK.value(), ResultMsg.SUCCESS, data);
    }

    public static ResultDto success(){
        return  new ResultDto(HttpStatus.OK.value(), ResultMsg.SUCCESS);
    }

    public static ResultDto fail(String msg){
        return new ResultDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static ResultDto fail(){
        return new ResultDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResultMsg.FAIL);
    }


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
