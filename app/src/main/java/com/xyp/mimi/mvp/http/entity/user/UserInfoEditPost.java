package com.xyp.mimi.mvp.http.entity.user;

public class UserInfoEditPost {

    private String  UserId;
    private String Token;
    private String UserNick;
    private String Birthday;
    private int Sex;

    public UserInfoEditPost(String userId, String token, String userNick, String birthday, int sex) {
        UserId = userId;
        Token = token;
        UserNick = userNick;
        Birthday = birthday;
        Sex = sex;
    }
}
