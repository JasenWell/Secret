package com.xyp.mimi.mvp.http.entity.wallet;

public class TixianPost {

    private String UserId;
    private String Token;
    private String Amount;
    private String BankId;

    public TixianPost(String userId, String token, String amount, String bankId) {
        UserId = userId;
        Token = token;
        Amount = amount;
        BankId = bankId;
    }
}
