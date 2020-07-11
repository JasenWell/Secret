package com.xyp.mimi.mvp.http.entity.user;

public class UserPayPasswordPost {

    private  String UserId;
    private  String Token;
    private  String Mobile;
    private  String SecondPassWord;
    private  String VerifyCode;

    public UserPayPasswordPost(String userId, String token, String mobile, String secondPassWord, String verifyCode) {
        UserId = userId;
        Token = token;
        Mobile = mobile;
        SecondPassWord = secondPassWord;
        VerifyCode = verifyCode;
    }
}
