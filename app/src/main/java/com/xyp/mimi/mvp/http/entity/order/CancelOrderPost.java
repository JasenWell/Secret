package com.xyp.mimi.mvp.http.entity.order;

public class CancelOrderPost {

    private String  UserId;
    private String Token;
    private String OrderNo;

    public CancelOrderPost(String userId, String token, String orderNo) {
        UserId = userId;
        Token = token;
        OrderNo = orderNo;
    }
}
