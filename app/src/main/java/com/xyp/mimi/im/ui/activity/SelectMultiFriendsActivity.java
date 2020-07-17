package com.xyp.mimi.im.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.xyp.mimi.R;
import com.xyp.mimi.im.bean.ResponseAddingFriendInfo;
import com.xyp.mimi.im.bean.ResponseFriendInfo;
import com.xyp.mimi.im.bean.ResponseWrapperInfo;
import com.xyp.mimi.im.net.hjh.HttpHelper;
import com.xyp.mimi.im.net.hjh.ResponseJson;
import com.xyp.mimi.im.net.hjh.callback.IBaseCallBack;
import com.xyp.mimi.im.net.hjh.imp.AsynModelImp;
import com.xyp.mimi.im.sp.UserCache;
import com.xyp.mimi.im.ui.fragment.SelectMultiFriendFragment;
import com.xyp.mimi.im.ui.interfaces.OnSelectCountChangeListener;
import com.xyp.mimi.im.ui.view.SealTitleBar;
import com.xyp.mimi.im.viewmodel.SelectMultiViewModel;

/**
 * 不要直接请求此 Activity
 */
public class SelectMultiFriendsActivity extends SelectBaseActivity implements View.OnClickListener, OnSelectCountChangeListener, IBaseCallBack {
    private SelectMultiFriendFragment selectMultiFriendFragment;
    private SelectMultiViewModel selectMultiViewModel;
    private TextView titleConfirmTv;

    private AsynModelImp asynModelImp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SealTitleBar sealTitleBar = getTitleBar();
        titleConfirmTv = sealTitleBar.getTvRight();
        titleConfirmTv.setText(R.string.seal_select_confirm);
        titleConfirmTv.setOnClickListener(this);
        selectMultiFriendFragment = getSelectMultiFriendFragment();
        selectMultiFriendFragment.setOnSelectCountChangeListener(this);
        sealTitleBar.setTitle(getString(R.string.seal_select_group_member));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_fragment_container, selectMultiFriendFragment);
        transaction.commit();

        initViewModel();
    }

    private void initViewModel() {
        selectMultiViewModel = ViewModelProviders.of(this).get(SelectMultiViewModel.class);

        selectMultiViewModel.getSelectedCount().observe(this, selectCount -> {
            if (selectCount > 0) {
                setConfirmEnable(true);
            } else if (confirmEnabledWhenNoChecked()) {
                setConfirmEnable(true);
            } else {
                setConfirmEnable(false);
            }
        });
        asynModelImp = new AsynModelImp(this);

        asynModelImp.searchFriendList(HttpHelper.BUSINESS.REQUEST_FRIEND_LIST, UserCache.getInstance().getCurrentUserId());
    }

    /**
     * 设置可以点击确定
     *
     * @param isEnable
     */
    private void setConfirmEnable(boolean isEnable) {
        if (isEnable) {
            titleConfirmTv.setClickable(true);
            titleConfirmTv.setTextColor(getResources().getColor(android.R.color.white));
        } else {
            titleConfirmTv.setClickable(false);
            titleConfirmTv.setTextColor(getResources().getColor(android.R.color.darker_gray));
        }
    }


    protected SelectMultiFriendFragment getSelectMultiFriendFragment() {
        return new SelectMultiFriendFragment();
    }

    /**
     * @param v 右上角点击
     */
    @Override
    public void onClick(View v) {
        onConfirmClicked(selectMultiFriendFragment.getCheckedList(), selectMultiFriendFragment.getCheckedInitGroupList());
    }

    /**
     * 右下角点击事件
     */
    @Override
    protected void onConfirmClick() {
        onConfirmClicked(selectMultiFriendFragment.getCheckedList(), selectMultiFriendFragment.getCheckedInitGroupList());
    }


    @Override
    public void onSelectCountChange(int groupCount, int userCount) {
    }

    public ArrayList<String> getCheckedFriendIds() {
        return selectMultiFriendFragment.getCheckedFriendList();
    }

    public ArrayList<String> getCheckedGroupIds() {
        return selectMultiFriendFragment.getCheckedGroupList();
    }

    /**
     * 是否在没有选择时可以点击确定,默认为未选择时不可点击
     * 重写此方法以开启状态在未选择时可点击确认
     *
     * @return
     */
    public boolean confirmEnabledWhenNoChecked() {
        return false;
    }

    @Override
    protected boolean isSearchable() {
        return true;
    }

    @Override
    public void onSearch(String keyword) {
        if(selectMultiFriendFragment != null){
            if(TextUtils.isEmpty(keyword)){
                selectMultiFriendFragment.loadAll();
            }else {
                selectMultiFriendFragment.search(keyword);
            }
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onSuccess(Object object, int type) {
        ResponseJson responseJson = (ResponseJson) object;
        if(type == HttpHelper.BUSINESS.REQUEST_SEARCH_FRIEND_REQUEST.getCode()){
            ResponseWrapperInfo wrapperInfo = (ResponseWrapperInfo) responseJson.getData();
            if(wrapperInfo != null){

            }
        }else if(type == HttpHelper.BUSINESS.REQUEST_FRIEND_LIST.getCode()){
            ResponseWrapperInfo wrapperInfo = (ResponseWrapperInfo) responseJson.getData();

            Log.d("SelectMultiFriendsActivity",wrapperInfo.getFriendslist().get(0).getImgUrl());
        }
    }

}
