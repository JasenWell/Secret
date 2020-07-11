package com.xyp.mimi.mvp.http.entity.cart;

public class CartListPost {

    private  String UserId;
    private  String Token;

    public CartListPost(String userId, String token) {
        UserId = userId;
        Token = token;
    }


}
