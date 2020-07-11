package com.xyp.mimi.mvp.http.entity.market;

public class GoodsDetailPost {

    private String UserId;
    private String Token;
    private int Id;
    private String Lat;
    private String Lng;

    public GoodsDetailPost(String userId, String token, int id) {
        UserId = userId;
        Token = token;
        Id = id;
    }
}
