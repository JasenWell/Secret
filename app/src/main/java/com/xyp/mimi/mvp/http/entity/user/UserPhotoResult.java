package com.xyp.mimi.mvp.http.entity.user;

public class UserPhotoResult {

    /**
     * code : 0
     * data : http://shop.dadanyipin.com/upload/Avatar/2020051915320269464257.jpg
     * msg : 上传头像成功
     */

    private int code;
    private String data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
