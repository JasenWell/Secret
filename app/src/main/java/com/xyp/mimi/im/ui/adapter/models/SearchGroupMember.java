package com.xyp.mimi.im.ui.adapter.models;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.xyp.mimi.im.bean.ResponseGroupInfo;
import com.xyp.mimi.im.db.model.GroupEntity;

public class SearchGroupMember {

    @Embedded
    private ResponseGroupInfo groupEntity;

    @ColumnInfo(name = "member_id")
    private String memberId;

    @ColumnInfo(name = "nickname")
    private String nickName;

    public ResponseGroupInfo getGroupEntity() {
        return groupEntity;
    }

    public void setGroupEntity(ResponseGroupInfo groupEntity) {
        this.groupEntity = groupEntity;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
