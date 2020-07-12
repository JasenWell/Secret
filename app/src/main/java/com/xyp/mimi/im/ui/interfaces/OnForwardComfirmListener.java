package com.xyp.mimi.im.ui.interfaces;

import java.util.List;

import com.xyp.mimi.im.db.model.FriendShipInfo;
import com.xyp.mimi.im.db.model.GroupEntity;

public interface OnForwardComfirmListener {
    void onForward(List<GroupEntity> groups, List<FriendShipInfo> friendShipInfos);
    void onForwardNoDialog(List<GroupEntity> groups, List<FriendShipInfo> friendShipInfos);
}
