package com.xyp.mimi.mvp.http.entity.user;

public class UserEditPost {

    private String  UserId;
    private String Token;
    private String UserNick;

    public UserEditPost(String userId, String token, String userNick) {
        UserId = userId;
        Token = token;
        UserNick = userNick;
    }
}
