package com.xyp.mimi.im.bean;

import java.io.Serializable;

/**
 * Description:
 * Created by hjh on 2020/7/13.
 */
public class ResponseUserInfo implements Serializable {

    private String account;// "13402375956",
    private String activityLevel;//0,
    private String count;// 0,
    private String countr;//0,
    private String createTime;//1594632487000,
    private String dllevel;//0,
    private String dllevels;//"V0",
    private String experience;//0,
    private String id;//73,
    private String integral;//0,
    private String level;//"0",
    private String levelCount;// 0,
    private String levelStr;//0,
    private String money;// 100000.0,
    private String password;//"123456",
    private String payPassword;//"123456",
    private String rechargeCount;//0.0,
    private String status;// 1,
    private String userName;//"hjh"

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCountr() {
        return countr;
    }

    public void setCountr(String countr) {
        this.countr = countr;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDllevel() {
        return dllevel;
    }

    public void setDllevel(String dllevel) {
        this.dllevel = dllevel;
    }

    public String getDllevels() {
        return dllevels;
    }

    public void setDllevels(String dllevels) {
        this.dllevels = dllevels;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelCount() {
        return levelCount;
    }

    public void setLevelCount(String levelCount) {
        this.levelCount = levelCount;
    }

    public String getLevelStr() {
        return levelStr;
    }

    public void setLevelStr(String levelStr) {
        this.levelStr = levelStr;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getRechargeCount() {
        return rechargeCount;
    }

    public void setRechargeCount(String rechargeCount) {
        this.rechargeCount = rechargeCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
