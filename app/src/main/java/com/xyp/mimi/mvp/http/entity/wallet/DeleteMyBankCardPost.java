package com.xyp.mimi.mvp.http.entity.wallet;

public class DeleteMyBankCardPost {
    /**
     * UserId	用户登录后返回的Id
     * Token	登录后返回的Token
     * Id	银行卡唯一Id
     */
    private  String UserId;
    private  String Token;
    private  int Id;

    public DeleteMyBankCardPost(String userId, String token, int id) {
        UserId = userId;
        Token = token;
        Id = id;
    }
}
