package com.xyp.mimi.im.bean;

import com.xyp.mimi.im.model.GroupMember;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:包装选中的朋友和群组
 * Created by hjh on 2020/7/17.
 */
public class CheckedItem implements Serializable {


    private List<ResponseAddingFriendInfo> friendInfoList = new ArrayList<>();
    private List<GroupMember> groupMemberList = new ArrayList<>();

    public List<ResponseAddingFriendInfo> getFriendInfoList() {
        return friendInfoList;
    }

    public void setFriendInfoList(List<ResponseAddingFriendInfo> friendInfoList) {
        this.friendInfoList = friendInfoList;
    }

    public List<GroupMember> getGroupMemberList() {
        return groupMemberList;
    }

    public void setGroupMemberList(List<GroupMember> groupMemberList) {
        this.groupMemberList = groupMemberList;
    }
}
