package com.xyp.mimi.mvp.http.entity.user;

public class UserRegisterPost {

    private  String mobile;
    private  String password;

    public UserRegisterPost(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }
}
