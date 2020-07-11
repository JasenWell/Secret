package com.xyp.mimi.mvp.http.entity.user;

public class UserLoginPasswordPost {

    private  String UserId;
    private  String Token;
    private  String Mobile;
    private  String SecondPassWord;
    private  String VerifyCode;

    public UserLoginPasswordPost(String userId, String token, String mobile, String secondPassWord, String verifyCode) {
        UserId = userId;
        Token = token;
        Mobile = mobile;
        SecondPassWord = secondPassWord;
        VerifyCode = verifyCode;
    }
}
