package com.xyp.mimi.im.ui.interfaces;

import com.xyp.mimi.im.bean.ResponseUserInfo;
import com.xyp.mimi.im.model.SearchFriendInfo;

public interface OnSearchFriendItemClickListener {
    /**
     * 搜索结果被点击
     *
     * @param searchFriendInfo
     */
    void onSearchFriendItemClick(ResponseUserInfo searchFriendInfo);
}
