package com.xyp.mimi.im.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GroupResult implements Serializable {
    @SerializedName(value = "id", alternate = {"groupId"})
    public String id;

    public List<AddMemberResult> userStatus;

    private String gid;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }
}
