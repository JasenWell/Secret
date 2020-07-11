package com.xyp.mimi.mvp.http.entity.wallet;

//我的收益
public class MyEarningResult {

    /**
     * { "code": 0, "msg": "获取成功", "data":{ KTAmount：可提收益 ，JTAmount：今日收益， ZTAmount：昨日收益 ，LJAmount：累计收益 } }
     */

    private String KTAmount;
    private String JTAmount;
    private String ZTAmount;
    private String LJAmount;

    public String getKTAmount() {
        return KTAmount;
    }

    public void setKTAmount(String KTAmount) {
        this.KTAmount = KTAmount;
    }

    public String getJTAmount() {
        return JTAmount;
    }

    public void setJTAmount(String JTAmount) {
        this.JTAmount = JTAmount;
    }

    public String getZTAmount() {
        return ZTAmount;
    }

    public void setZTAmount(String ZTAmount) {
        this.ZTAmount = ZTAmount;
    }

    public String getLJAmount() {
        return LJAmount;
    }

    public void setLJAmount(String LJAmount) {
        this.LJAmount = LJAmount;
    }
}

