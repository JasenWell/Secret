package com.xyp.mimi.mvp.http.entity.order;

public class OrderDetailPost {

    private String  UserId;
    private String Token;
    private String OrderNo;

    public OrderDetailPost(String userId, String token, String orderNo) {
        UserId = userId;
        Token = token;
        OrderNo = orderNo;
    }
}
