package com.xyp.mimi.im.bean;

import java.io.Serializable;

/**
 * Description:
 * Created by hjh on 2020/7/18.
 */
public class ResponseGroupInfo implements Serializable {


    private String id;// "35f4c981374e4976a33cf6a3ee3545ee",群id?
    private String uid;//99,  群主
    private String context;//"ggf", 群名
    private String createTime;// 1595006953000,
    private String uidList;//"99,100,103",
    private String userList;//null

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUidList() {
        return uidList;
    }

    public void setUidList(String uidList) {
        this.uidList = uidList;
    }

    public String getUserList() {
        return userList;
    }

    public void setUserList(String userList) {
        this.userList = userList;
    }
}
