package com.xyp.mimi.im.bean;

import java.io.Serializable;

/**
 * Description:搜索好友结果
 * Created by hjh on 2020/7/14.
 */
public class ResponseSearchFriendInfo implements Serializable {

    private String isnot;
    private String msg;
    private String viceUid;


    public String getIsnot() {
        return isnot;
    }

    public void setIsnot(String isnot) {
        this.isnot = isnot;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getViceUid() {
        return viceUid;
    }

    public void setViceUid(String viceUid) {
        this.viceUid = viceUid;
    }
}
