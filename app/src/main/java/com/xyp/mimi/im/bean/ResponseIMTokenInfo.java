package com.xyp.mimi.im.bean;

import java.io.Serializable;

/**
 * Description:
 * Created by hjh on 2020/7/15.
 */
public class ResponseIMTokenInfo implements Serializable {
    private int code;//200;
    private String userId;//jlk456j5",
    private String token;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
