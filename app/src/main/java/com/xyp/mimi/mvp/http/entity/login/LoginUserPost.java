package com.xyp.mimi.mvp.http.entity.login;

public class LoginUserPost {
    private String account;

    private String password;

    public LoginUserPost(String account) {
        this.account = account;
    }


    public LoginUserPost(String account, String password) {
        this.account = account;
        this.password = password;
    }

}
