package com.xyp.mimi.mvp.http.entity.wallet;

public class MoneyDetailPost {

    private String UserId;
    private String Token;
    private int Page;
    private int PageSize;
    private int Type;
    private String Date;

    public MoneyDetailPost() {

    }

    public MoneyDetailPost(String userId, String token, int page, int pageSize, String date) {
        UserId = userId;
        Token = token;
        Page = page;
        PageSize = pageSize;
        Date = date;
    }

    public MoneyDetailPost(String userId, String token, int page, int pageSize, int type, String date) {
        UserId = userId;
        Token = token;
        Page = page;
        PageSize = pageSize;
        Type = type;
        Date = date;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public int getPage() {
        return Page;
    }

    public void setPage(int page) {
        Page = page;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
