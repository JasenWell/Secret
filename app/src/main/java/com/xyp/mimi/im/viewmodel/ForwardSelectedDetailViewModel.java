package com.xyp.mimi.im.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import java.util.List;

import com.xyp.mimi.R;
import com.xyp.mimi.im.db.model.FriendShipInfo;
import com.xyp.mimi.im.db.model.GroupEntity;
import com.xyp.mimi.im.ui.adapter.models.ListItemModel;
import com.xyp.mimi.im.ui.adapter.viewholders.ForwardSelectedViewHolder;

public class ForwardSelectedDetailViewModel extends CommonListBaseViewModel {

    public ForwardSelectedDetailViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void loadData() {

    }

    public void loadData(List<FriendShipInfo>  selectedFriends, List<GroupEntity> seletedGroup) {
        ModelBuilder builder = new ModelBuilder();
        builder.addFriendList(selectedFriends);
        builder.addGroupList(seletedGroup);
        builder.post();
    }

    /**
     * 创建联系人对象.
     *
     * @param info
     * @return
     */
    @Override
    protected ListItemModel createFriendModel(FriendShipInfo info) {
        final ListItemModel friendModel = super.createFriendModel(info);
        ListItemModel.ItemView itemView = new ListItemModel
                .ItemView(R.layout.item_group_management, ListItemModel.ItemView.Type.FRIEND, ForwardSelectedViewHolder.class);
        friendModel.setItemView(itemView);
        return friendModel;
    }


    @Override
    protected ListItemModel createGroupModel(GroupEntity entity) {
        final ListItemModel groupModel = super.createGroupModel(entity);
        ListItemModel.ItemView itemView = new ListItemModel
                .ItemView(R.layout.item_group_management, ListItemModel.ItemView.Type.GROUP, ForwardSelectedViewHolder.class);
        groupModel.setItemView(itemView);
        return groupModel;
    }
}
