package com.xyp.mimi.im.net.hjh.callback;


import com.xyp.mimi.im.net.hjh.HttpHelper;

public interface IAsynModel {

    void addFriendRequest(HttpHelper.BUSINESS business, String mianUid, String phone);//mianUid 登录用户id

    void agreeFriendRequest(HttpHelper.BUSINESS business, String viceUid, String mianUid);//viceUid 登录用户id

    void searchFriendRequest(HttpHelper.BUSINESS business,String userId);

    void startSingleChat(HttpHelper.BUSINESS business, String myId,String hisId,String content);

    void searchChatRecord(HttpHelper.BUSINESS business, String myId,String hisId);

    void searchFriendList(HttpHelper.BUSINESS business,String userId);




}
