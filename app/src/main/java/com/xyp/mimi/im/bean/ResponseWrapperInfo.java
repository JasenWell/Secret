package com.xyp.mimi.im.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Created by hjh on 2020/7/14.
 */
public class ResponseWrapperInfo implements Serializable {

    private List<ResponseAddingFriendInfo> Friendslist;


    public List<ResponseAddingFriendInfo> getFriendslist() {
        return Friendslist;
    }

    public void setFriendslist(List<ResponseAddingFriendInfo> friendslist) {
        Friendslist = friendslist;
    }
}
