package com.xyp.mimi.im.ui.adapter.viewholders;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xyp.mimi.R;
import com.xyp.mimi.im.bean.ResponseAddingFriendInfo;
import com.xyp.mimi.im.db.model.FriendDetailInfo;
import com.xyp.mimi.im.db.model.FriendShipInfo;
import com.xyp.mimi.im.model.GroupMember;
import com.xyp.mimi.im.ui.adapter.models.CheckableContactModel;
import com.xyp.mimi.im.ui.interfaces.OnCheckContactClickListener;
import com.xyp.mimi.im.ui.widget.SelectableRoundedImageView;
import com.xyp.mimi.im.utils.ImageLoaderUtils;

public class CheckableContactViewHolder extends CheckableBaseViewHolder<CheckableContactModel> {

    private TextView nameTextView;
    private SelectableRoundedImageView protraitImageView;
    private OnCheckContactClickListener checkableItemClickListener;
    private CheckableContactModel model;
    private ImageView checkBox;


    public CheckableContactViewHolder(@NonNull View itemView, OnCheckContactClickListener listener) {
        super(itemView);
        checkableItemClickListener = listener;
        protraitImageView = itemView.findViewById(R.id.iv_portrait);
        nameTextView = itemView.findViewById(R.id.tv_contact_name);
        checkBox = itemView.findViewById(R.id.cb_select);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkableItemClickListener.onContactContactClick(model);
            }
        });
    }

    @Override
    public void update(CheckableContactModel contactModel) {
        model = contactModel;
        String name = null;
        String portraitUrl = null;
        if (contactModel.getBean() instanceof ResponseAddingFriendInfo) {
            ResponseAddingFriendInfo friendShipInfo = (ResponseAddingFriendInfo) contactModel.getBean();
            String groupDisplayName = "";
            String displayName = friendShipInfo.getUsername();
            if (!TextUtils.isEmpty(groupDisplayName)) {
                name = groupDisplayName;
            } else if (!TextUtils.isEmpty(displayName)) {
                name = displayName;
            } else {
                name = "****";
            }
            portraitUrl = friendShipInfo.getImgUrl();
        } else if (contactModel.getBean() instanceof GroupMember) {
            GroupMember groupMember = (GroupMember) contactModel.getBean();
            name = groupMember.getGroupNickName();
            if (TextUtils.isEmpty(name)) {
                name = groupMember.getName();
            }
            portraitUrl = groupMember.getPortraitUri();
        }

        nameTextView.setText(name);
        ImageLoaderUtils.displayUserPortraitImage(portraitUrl, protraitImageView);
        updateCheck(checkBox, contactModel.getCheckType());
    }

}
