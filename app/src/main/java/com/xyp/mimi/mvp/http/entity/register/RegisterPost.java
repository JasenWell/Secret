package com.xyp.mimi.mvp.http.entity.register;

public class RegisterPost {

    private String mobile;//手机号
    private String yzcode;//验证码
    private String password;//密码
    private String InviteCode;//邀请码

    public RegisterPost(String mobile, String yzcode, String password) {
        this.mobile = mobile;
        this.yzcode = yzcode;
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getYzcode() {
        return yzcode;
    }

    public void setYzcode(String yzcode) {
        this.yzcode = yzcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInviteCode() {
        return InviteCode;
    }

    public void setInviteCode(String inviteCode) {
        InviteCode = inviteCode;
    }


    public RegisterPost(String mobile, String yzcode, String password, String inviteCode) {
        this.mobile = mobile;
        this.yzcode = yzcode;
        this.password = password;
        InviteCode = inviteCode;
    }
}
