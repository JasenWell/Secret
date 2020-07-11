package com.xyp.mimi.mvp.http.entity.user;

public class UserPost {

    private String  UserId;
    private String Token;
    private int Page;
    private int PageSize;
    private int Id;
    public UserPost(String userId, String token) {
        UserId = userId;
        Token = token;
    }

    public UserPost(String userId, String token, int id) {
        UserId = userId;
        Token = token;
        Id = id;
    }

    public UserPost(String userId, String token, int page, int pageSize) {
        UserId = userId;
        Token = token;
        Page = page;
        PageSize = pageSize;
    }
}
