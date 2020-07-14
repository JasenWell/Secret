package com.xyp.mimi.im.bean;

import java.io.Serializable;

/**
 * Description:正在添加的好友
 * Created by hjh on 2020/7/14.
 */
public class ResponseAddingFriendInfo implements Serializable {

    private String id;


    private String mianUid;//73,自己
    private String username;//null,
    private String imgUrl;//null,
    private String viceUid;//74,
    private String createTime;//1594734490000,
    private String status;// 0 (0.添加状态1.正常,2.拉黑)


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMianUid() {
        return mianUid;
    }

    public void setMianUid(String mianUid) {
        this.mianUid = mianUid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getViceUid() {
        return viceUid;
    }

    public void setViceUid(String viceUid) {
        this.viceUid = viceUid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
