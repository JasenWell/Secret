package com.xyp.mimi.mvp.http.entity.order;

public class OrderListPost {

    private  String UserId;
    private  String Token;
    private  int Page;
    private  int PageSize;
    private  int Status;

    public OrderListPost(String userId, String token, int page, int pageSize, int status) {
        UserId = userId;
        Token = token;
        Page = page;
        PageSize = pageSize;
        Status = status;
    }
}
