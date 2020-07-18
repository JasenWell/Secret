package com.xyp.mimi.im.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.im.bean.ResponseSearchFriendInfo;
import com.xyp.mimi.im.bean.ResponseUserInfo;
import com.xyp.mimi.im.common.IntentExtra;
import com.xyp.mimi.im.event.MessageEvent;
import com.xyp.mimi.im.im.IMManager;
import com.xyp.mimi.im.model.AddFriendResult;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.model.SearchFriendInfo;
import com.xyp.mimi.im.model.Status;
import com.xyp.mimi.im.net.hjh.HttpHelper;
import com.xyp.mimi.im.sp.UserCache;
import com.xyp.mimi.im.ui.dialog.SimpleInputDialog;
import com.xyp.mimi.im.ui.fragment.SearchFriendNetFragment;
import com.xyp.mimi.im.ui.fragment.SearchFriendResultFragment;
import com.xyp.mimi.im.ui.interfaces.OnSearchFriendClickListener;
import com.xyp.mimi.im.ui.interfaces.OnSearchFriendItemClickListener;
import com.xyp.mimi.im.viewmodel.SearchFriendNetViewModel;
import com.xyp.mimi.mvp.http.entity.login.LoginUserResult;

import org.greenrobot.eventbus.EventBus;

import io.rong.imkit.RongIM;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imlib.model.UserInfo;

public class SearchFriendActivity extends TitleBaseActivity implements OnSearchFriendClickListener,
        OnSearchFriendItemClickListener {
    private SearchFriendNetFragment searchFriendFragment;
    private SearchFriendResultFragment searchFriendResultFragment;
    private SearchFriendNetViewModel viewModel;
    private boolean isFriend;
    private int containerId = R.id.fl_fragment_container;//R.id.layout_container

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_search_content);
        getTitleBar().setTitle((R.string.seal_main_title_add_friends));
        getTitleBar().setOnBtnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        searchFriendFragment = new SearchFriendNetFragment();
        searchFriendFragment.setOnSearchFriendClickListener(this);
        getSupportFragmentManager().beginTransaction() .add(containerId, searchFriendFragment).commit();
        viewModel = ViewModelProviders.of(this).get(SearchFriendNetViewModel.class);
        viewModel.getSearchFriendByphone().observe(this, new Observer<Resource<ResponseUserInfo>>() {
            @Override
            public void onChanged(Resource<ResponseUserInfo> searchFriendInfoResource) {
                if (searchFriendInfoResource.status == Status.SUCCESS) {
                    ResponseUserInfo friendInfo = searchFriendInfoResource.data;
                    searchFriendResultFragment = new SearchFriendResultFragment();
                    searchFriendResultFragment.setData(SearchFriendActivity.this, friendInfo);
                    pushFragment(searchFriendResultFragment);
                    viewModel.isFriend(friendInfo);
                } else if (searchFriendInfoResource.status == Status.ERROR) {
                    Toast.makeText(SearchFriendActivity.this, R.string.seal_account_not_exist, Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getIsFriend().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                isFriend = aBoolean;
            }
        });
        viewModel.getAddFriend().observe(this, new Observer<Resource<ResponseSearchFriendInfo>>() {
            @Override
            public void onChanged(Resource<ResponseSearchFriendInfo> addFriendResultResource) {
                if (addFriendResultResource.status == Status.SUCCESS) {
                    showToast(R.string.common_request_success);
                    ArmsUtils.snackbarText("添加好友请求已发送");
                    EventBus.getDefault().post(new MessageEvent("添加好友请求成功",MessageEvent.EventType.REFRESH_FRIEND_LIST));
                    finish();
                } else if (addFriendResultResource.status == Status.ERROR) {
                    Toast.makeText(SearchFriendActivity.this,
                            String.format(getString(R.string.seal_quest_failed_error_code), addFriendResultResource.code),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void pushFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, searchFriendResultFragment);
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commit();
    }

    @Override
    public void onSearchClick(String region, String searchContent) {
        if(TextUtils.isDigitsOnly(searchContent)){
            viewModel.searchFriendByphone(searchContent);
        } else {
            viewModel.searchFriendByphone(searchContent);
        }
    }

    @Override
    public void onSearchFriendItemClick(ResponseUserInfo searchFriendInfo) {
        if (isFriend || searchFriendInfo.getUid().equals(RongIM.getInstance().getCurrentUserId())) {
            toDetailActivity(searchFriendInfo.getUid());
        } else {
            showAddFriendDialog(searchFriendInfo.getAccount());
        }
    }

    private void toDetailActivity(String userId) {
        Intent intent = new Intent(this, UserDetailActivity.class);
        intent.putExtra(IntentExtra.STR_TARGET_ID, userId);
        startActivity(intent);
    }

    private void showAddFriendDialog(String phone) {
        final EditText et = new EditText(this);
        SimpleInputDialog dialog = new SimpleInputDialog();
        dialog.setInputHint(getString(R.string.profile_add_friend_hint));
        dialog.setInputDialogListener(new SimpleInputDialog.InputDialogListener() {
            @Override
            public boolean onConfirmClicked(EditText input) {
                String inviteMsg = input.getText().toString();
                UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(IMManager.getInstance().getCurrentId());
                // 如果邀请信息为空则使用默认邀请语
                if (TextUtils.isEmpty(inviteMsg) && userInfo != null) {
                    // 当有附带群组名时显示来自哪个群组，没有时仅带自己的昵称
                    inviteMsg = getString(R.string.profile_invite_friend_description_format, userInfo.getName());
                }
                viewModel.addFriendRequest(UserCache.getInstance().getCurrentUserId(), phone);
                return true;
            }
        });
        dialog.show(getSupportFragmentManager(), null);

    }


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return 0;//R.layout.activity_friend_search_content;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
