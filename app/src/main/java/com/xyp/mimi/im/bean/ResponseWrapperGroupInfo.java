package com.xyp.mimi.im.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Description:查询群信息返回的数据
 * Created by hjh on 2020/7/19.
 */
public class ResponseWrapperGroupInfo implements Serializable {

    private ResponseGroupInfo groupList;
    private List<ResponseGroupChatInfo> groupChatList;

    public ResponseGroupInfo getGroupList() {
        return groupList;
    }

    public void setGroupList(ResponseGroupInfo groupList) {
        this.groupList = groupList;
    }

    public List<ResponseGroupChatInfo> getGroupChatList() {
        return groupChatList;
    }

    public void setGroupChatList(List<ResponseGroupChatInfo> groupChatList) {
        this.groupChatList = groupChatList;
    }
}
