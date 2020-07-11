package com.xyp.mimi.mvp.http.entity.address;

public class AddressDeletePost {

    private  String UserId;
    private  String Token;
    private  int Id;

    public AddressDeletePost(String userId, String token, int id) {
        UserId = userId;
        Token = token;
        Id = id;
    }
}
