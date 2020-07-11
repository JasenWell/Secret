package com.xyp.mimi.mvp.http.entity.invoice;

public class InvoiceDeletePost {

    private  String UserId;
    private  String Token;
    private int Id;


    public InvoiceDeletePost(String userId, String token, int id) {
        UserId = userId;
        Token = token;
        Id = id;
    }
}
