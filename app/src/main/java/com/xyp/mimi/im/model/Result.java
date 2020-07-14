package com.xyp.mimi.im.model;

import com.google.gson.annotations.SerializedName;
import com.xyp.mimi.im.common.ErrorCode;
import com.xyp.mimi.im.common.NetConstant;

/**
 * 网络请求结果基础类
 * @param <T> 请求结果的实体类
 */
public class Result<T> {
    public int count;
    public int code;

    @SerializedName("data")
    public T result;


    private String msg;

    public Result(){
    }

    public Result(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess(){
        return code == NetConstant.REQUEST_SUCCESS_CODE;
    }

    public String getErrorMessage(){
        return ErrorCode.fromCode(code).getMessage();
    }
}
