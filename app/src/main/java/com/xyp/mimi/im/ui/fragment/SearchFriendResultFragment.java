package com.xyp.mimi.im.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xyp.mimi.R;
import com.xyp.mimi.im.bean.ResponseUserInfo;
import com.xyp.mimi.im.model.SearchFriendInfo;
import com.xyp.mimi.im.ui.interfaces.OnSearchFriendItemClickListener;
import com.xyp.mimi.im.utils.ImageLoaderUtils;
import com.xyp.mimi.im.utils.RongGenerate;

public class SearchFriendResultFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "SearchFriendResultFragment";
    private ResponseUserInfo searchFriendInfo;
    private ImageView ivPortrait;
    private TextView tvName;
    private TextView tvStAccount;
    private OnSearchFriendItemClickListener searchFriendItemClickListener;

    public void setData(OnSearchFriendItemClickListener listener, ResponseUserInfo searchFriendInfo){
        this.searchFriendInfo = searchFriendInfo;
        searchFriendItemClickListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search_friend_result_net, container, false);
        ivPortrait = view.findViewById(R.id.search_header);
        tvName = view.findViewById(R.id.search_name);
        tvStAccount = view.findViewById(R.id.search_sealtalk_acctount);
        view.setOnClickListener(this);
        initView();
        return view;
    }

    private void initView() {
        tvName.setText(searchFriendInfo.getUserName());
        String stAccount = "";
        if (!TextUtils.isEmpty(stAccount)) {
            tvStAccount.setText(getString(R.string.seal_st_account_content_format, stAccount));
        }
        String portraitUri = searchFriendInfo.getImgUrl();
        if (TextUtils.isEmpty(portraitUri)) {
            String generateDefaultAvatar = RongGenerate.generateDefaultAvatar(getContext(), searchFriendInfo.getId(), searchFriendInfo.getUserName());
            ImageLoaderUtils.displayUserPortraitImage(generateDefaultAvatar, ivPortrait);
        } else {
            ImageLoaderUtils.displayUserPortraitImage(portraitUri, ivPortrait);
        }
    }

    @Override
    public void onClick(View v) {
        if (searchFriendItemClickListener != null) {
            searchFriendItemClickListener.onSearchFriendItemClick(searchFriendInfo);
        }
    }
}
