package com.xyp.mimi.mvp.http.entity.user;

public class UserPhotoPost {

    private String  UserId;
    private String Token;
    private String Avatar;

    public UserPhotoPost(String userId, String token, String avatar) {
        UserId = userId;
        Token = token;
        Avatar = avatar;
    }
}
