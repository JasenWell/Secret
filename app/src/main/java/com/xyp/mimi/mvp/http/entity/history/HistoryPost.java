package com.xyp.mimi.mvp.http.entity.history;

public class HistoryPost {

    private String UserId;
    private String Token;
    private int Page;
    private int PageSize;


    public HistoryPost(String userId, String token, int page, int pageSize) {
        UserId = userId;
        Token = token;
        Page = page;
        PageSize = pageSize;
    }



}
