package com.xyp.mimi.mvp.http.entity.login;

public class LoginUserResult {
    /**
     * UserId : 9D16ED168FF5930F
     * Token : 21343527B4FAA917EA632AAFBE9DDC34
     * Avatar :
     * NickName : lktau759
     * ReferralCode : 86R511
     */

    private String UserId;
    private String Token;
    private String Avatar;//头像
    private String NickName;//昵称
    private String ReferralCode;//邀请码



    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public String getReferralCode() {
        return ReferralCode;
    }

    public void setReferralCode(String ReferralCode) {
        this.ReferralCode = ReferralCode;
    }
}