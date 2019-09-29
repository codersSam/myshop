package com.senhua.my.shop.commons.dto;

import java.io.Serializable;

/**
 * 自定义返回结果
 */
public class BaseResult implements Serializable {
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;

    private int status;
    private String message;
    private Object data;

    public static BaseResult success(){
       return creatResult(STATUS_SUCCESS,"成功",null);
    }
    public static BaseResult success(String message){
        return creatResult(STATUS_SUCCESS,message,null);
    }
    public static BaseResult success(String message,Object data){
        return creatResult(STATUS_SUCCESS,message,data);
    }
    public static BaseResult fail(){
        return creatResult(STATUS_FAIL,"失败",null);
    }
    public static BaseResult fail(String message){
        return creatResult(STATUS_FAIL,message,null);
    }
    public static BaseResult fail(int status,String message){
        return creatResult(status,message,null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private static BaseResult creatResult(int status, String message, Object data){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        baseResult.setData(data);
        return baseResult;
    }
}
