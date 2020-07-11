package com.xyp.mimi.mvp.http.entity.address;

public class AddressListPost {

    private  String UserId;
    private  String Token;
    private  int Page;
    private  int PageSize;

    public AddressListPost(String userId, String token, int page, int pageSize) {
        UserId = userId;
        Token = token;
        Page = page;
        PageSize = pageSize;
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
}
