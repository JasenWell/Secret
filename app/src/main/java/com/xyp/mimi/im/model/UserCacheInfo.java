package com.xyp.mimi.im.model;

import com.xyp.mimi.im.db.model.UserInfo;

public class UserCacheInfo extends UserInfo {
    private CountryInfo countryInfo;
    private String loginToken;
    private String password;

    public UserCacheInfo(){

    }

    public UserCacheInfo(String id) {
        setId(id);
    }

    public UserCacheInfo(String id, String loginToken, String phoneNumber, String password, String region) {
        setId(id);
        setPhoneNumber(phoneNumber);
        setLoginToken(loginToken);
        setRegion(region);
        setPassword(password);
    }

    public void setUserInfo(UserInfo info) {
        if (getId() != null && info != null && !getId().equals(info.getId())) {
            return;
        }
        setId(info.getId());
        setPortraitUri(info.getPortraitUri());
        setName(info.getName());
        setNameSpelling(info.getNameSpelling());
        setAlias(info.getAlias());
        setAliasSpelling(info.getAliasSpelling());
        setRegion(info.getRegion());
        setPhoneNumber(info.getPhoneNumber());
        setFriendStatus(info.getFriendStatus());
        setOrderSpelling(info.getOrderSpelling());
    }

    public void setUserCacheInfo(UserCacheInfo info) {
        setId(info.getId());
        setPortraitUri(info.getPortraitUri());
        setName(info.getName());
        setNameSpelling(info.getNameSpelling());
        setAlias(info.getAlias());
        setAliasSpelling(info.getAliasSpelling());
        setRegion(info.getRegion());
        setPhoneNumber(info.getPhoneNumber());
        setFriendStatus(info.getFriendStatus());
        setOrderSpelling(info.getOrderSpelling());
        setLoginToken(info.getLoginToken());
        setPassword(info.getPassword());
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}





