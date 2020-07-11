package com.xyp.mimi.mvp.http.entity.wallet;

public class AddBankPost {
    /**
     * {
     * UserId,
     * Token,
     * Mobile,手机号
     * VerifyCode,验证码
     * BankName,银行名称
     * BankAddress,开户地址
     * BankCardNo,银行卡号
     * BankCardName,持卡人姓名
     * }
     */
    private   String UserId;
    private   String Token;
    private   String BankCardName;
    private   String BankNam;
    private   String BankCardNo;
    private   String BankAddress;
    private   String Mobile;
    private   String VerifyCode;

    public AddBankPost(String userId, String token) {
        UserId = userId;
        Token = token;
    }

    public AddBankPost(String userId, String token, String bankCardName, String bankNam, String bankCardNo, String bankAddress, String mobile, String verifyCode) {
        UserId = userId;
        Token = token;
        BankCardName = bankCardName;
        BankNam = bankNam;
        BankCardNo = bankCardNo;
        BankAddress = bankAddress;
        Mobile = mobile;
        VerifyCode = verifyCode;
    }
}
