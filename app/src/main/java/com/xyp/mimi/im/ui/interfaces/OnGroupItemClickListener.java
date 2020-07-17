package com.xyp.mimi.im.ui.interfaces;

import com.xyp.mimi.im.bean.ResponseGroupInfo;
import com.xyp.mimi.im.db.model.GroupEntity;

public interface OnGroupItemClickListener {
    void onGroupClicked(ResponseGroupInfo groupEntity);
}
