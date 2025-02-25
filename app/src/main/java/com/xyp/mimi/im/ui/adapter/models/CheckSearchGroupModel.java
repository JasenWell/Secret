package com.xyp.mimi.im.ui.adapter.models;

import java.util.List;

import com.xyp.mimi.im.bean.ResponseGroupInfo;
import com.xyp.mimi.im.db.model.GroupEntity;

public class CheckSearchGroupModel extends SearchGroupModel {
    private CheckType checkType = CheckType.NONE;

    public CheckSearchGroupModel(ResponseGroupInfo bean, int type, int groupNameStart, int groupNameEnd, List<GroupMemberMatch> memberMatches) {
        super(bean, type, groupNameStart, groupNameEnd, memberMatches);
    }

    public CheckType getCheckType() {
        return checkType;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }
}
