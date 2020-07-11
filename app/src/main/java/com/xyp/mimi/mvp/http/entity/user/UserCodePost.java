package com.xyp.mimi.mvp.http.entity.user;

public class UserCodePost {

    private String  UserId;
    private String Token;
    private String Mobile;
    private int  Type;

    public UserCodePost(String userId, String token, String mobile, int type) {
        UserId = userId;
        Token = token;
        Mobile = mobile;
        Type = type;
    }
}
