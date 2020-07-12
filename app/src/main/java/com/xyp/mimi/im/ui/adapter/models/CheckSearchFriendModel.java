package com.xyp.mimi.im.ui.adapter.models;

import com.xyp.mimi.im.db.model.FriendShipInfo;

public class CheckSearchFriendModel extends SearchFriendModel {
    private CheckType checkType = CheckType.NONE;

    public CheckSearchFriendModel(FriendShipInfo bean, int type, int nameStart, int nameEnd, int aliasStart, int aliasEnd) {
        super(bean, type, nameStart, nameEnd, aliasStart, aliasEnd);
    }

    public CheckType getCheckType() {
        return checkType;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }
}
