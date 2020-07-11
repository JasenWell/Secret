package com.xyp.mimi.mvp.http.entity.wallet;

//绑定银行卡获取验证码
public class CodePost {

    private String UserId;
    private String Token;
    private String Mobile;

    public CodePost(String userId, String token, String mobile) {
        UserId = userId;
        Token = token;
        Mobile = mobile;
    }


}
