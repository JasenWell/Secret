package com.xyp.mimi.im.bean;

import java.io.Serializable;

/**
 * Description:群聊聊天信息
 * Created by hjh on 2020/7/19.
 */
public class ResponseGroupChatInfo implements Serializable {

    private String id;//
    private String groupid;//"75e45443736b42faa00cec761d59dbf3"
    private String uid;//99,  发送人？
    private String context;//"ggf", 群名
    private String createTime;// 1595006953000,
    private String imgUrl;
    private String voiceUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }
}
