package com.xyp.mimi.mvp.http.entity.history;

public class HistoryDeletePost {


    private String UserId;
    private String Token;
    private String Id;


    public HistoryDeletePost(String userId, String token, String id) {
        UserId = userId;
        Token = token;
        Id = id;
    }
}
