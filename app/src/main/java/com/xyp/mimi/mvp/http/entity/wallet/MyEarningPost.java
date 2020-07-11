package com.xyp.mimi.mvp.http.entity.wallet;

//我的收益
public class MyEarningPost {

    private String UserId;
    private String Token;

    public MyEarningPost(String userId, String token) {
        UserId = userId;
        Token = token;
    }

}

