package com.xyp.mimi.im.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.im.bean.ResponseAddingFriendInfo;
import com.xyp.mimi.im.bean.ResponseWrapperInfo;
import com.xyp.mimi.im.db.model.FriendShipInfo;
import com.xyp.mimi.im.db.model.FriendStatus;
import com.xyp.mimi.im.event.MessageEvent;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.net.hjh.EConfig;
import com.xyp.mimi.im.net.hjh.HttpHelper;
import com.xyp.mimi.im.net.hjh.ResponseJson;
import com.xyp.mimi.im.sp.UserCache;
import com.xyp.mimi.im.ui.adapter.NewFriendListAdapter;
import com.xyp.mimi.im.viewmodel.NewFriendViewModel;

import org.greenrobot.eventbus.EventBus;


public class NewFriendListActivity extends TitleBaseActivity {

    private NewFriendListAdapter adapter;
    private NewFriendViewModel newFriendViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friendlist);
        initView();
        asynModelImp.searchFriendRequest(HttpHelper.BUSINESS.REQUEST_SEARCH_FRIEND_REQUEST, UserCache.getInstance().getCurrentUserId());
    }

    /**
     * 初始化布局
     */
    private void initView() {
        // title 设置
        getTitleBar().setTitle(R.string.new_friends);
        //左侧按钮监听
        getTitleBar().setOnBtnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 右侧按钮监听
//        getTitleBar().getBtnRight().setImageDrawable(getResources().getDrawable(R.drawable.seal_ic_new_friend_title_right));
//        getTitleBar().setOnBtnRightClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(NewFriendListActivity.this, SearchFriendActivity.class);
//                startActivity(intent);
//            }
//        });

        //新朋友列表
        ListView newFriendsList = findViewById(R.id.lv_new_friends_list);
        TextView isNull = findViewById(R.id.tv_is_null);
        newFriendsList.setEmptyView(isNull);
        adapter = new NewFriendListAdapter();
        // 点击监听
        adapter.setOnItemButtonClick(new NewFriendListAdapter.OnItemButtonClick() {
            @Override
            public boolean onButtonClick(View view, int position, ResponseAddingFriendInfo info) {
                asynModelImp.agreeFriendRequest(HttpHelper.BUSINESS.REQUEST_AGREE_FRIEND,UserCache.getInstance().getCurrentUserId(),info.getMianUid());
                return false;
            }

            @Override
            public boolean onIgnore(View view, int position, ResponseAddingFriendInfo info) {

                return false;
            }
        });
        newFriendsList.setAdapter(adapter);
    }




    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return 0;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onSuccess(Object object, int type) {
        ResponseJson responseJson = (ResponseJson) object;
        if(type == HttpHelper.BUSINESS.REQUEST_SEARCH_FRIEND_REQUEST.getCode()) {
            ResponseWrapperInfo wrapperInfo = (ResponseWrapperInfo) responseJson.getData();
            adapter.updateList(wrapperInfo.getFriendslist());
        }else if(type == HttpHelper.BUSINESS.REQUEST_AGREE_FRIEND.getCode()){
            showToast("已添加好友");
            EventBus.getDefault().post(new MessageEvent("已成功添加好友",MessageEvent.EventType.REFRESH_FRIEND_LIST));
            finish();
        }
    }
}
