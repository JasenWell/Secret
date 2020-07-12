package com.xyp.mimi.im.ui.interfaces;

import com.xyp.mimi.im.db.model.FriendShipInfo;

public interface OnContactItemClickListener {
    /**
     * 联系人列表人员点击监听
     *
     * @param friendShipInfo
     */
    void onItemContactClick(FriendShipInfo friendShipInfo);
}
