package com.xyp.mimi.im.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.im.common.IntentExtra;
import com.xyp.mimi.im.event.MessageEvent;
import com.xyp.mimi.im.model.qrcode.QrCodeDisplayType;
//import com.xyp.mimi.im.ui.dialog.CommonDialog;
import com.xyp.mimi.im.utils.CheckPermissionUtils;
//import com.xyp.mimi.im.wx.WXManager;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * 添加朋友界面
 */
public class AddFriendActivity extends TitleBaseActivity implements View.OnClickListener {
    private static final String TAG = "AddFriendActivity";
    private static final int REQUEST_PERMISSION_ADD_CONTACT_FREIND = 2001;
    private static final int REQUEST_PERMISSION_INVITE_CONTACT_FRIEND = 2002;

    @OnClick({R.id.add_friend_tv_search_friend})
    public void onViewClicked(View view) {
        if(view.getId() == R.id.add_friend_tv_search_friend){
            toSearchFriend();
        }

    }

    @Subscribe
    public void onEventMainThread(MessageEvent event){
        //接收到发布者发布的事件后，进行相应的处理操作
        if(event.getType() == MessageEvent.EventType.REFRESH_FRIEND_LIST){
            finish();
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_new_friend);
        getTitleBar().setTitle(R.string.new_friend_title);

        initView();
    }

    private void initView() {
        findViewById(R.id.add_friend_tv_search_friend).setOnClickListener(this);
//        findViewById(R.id.add_friend_tv_my_qrcode).setOnClickListener(this);
//        findViewById(R.id.add_friend_ll_add_from_contact).setOnClickListener(this);
//        findViewById(R.id.add_friend_ll_scan).setOnClickListener(this);
//        findViewById(R.id.add_friend_ll_add_from_wechat).setOnClickListener(this);
//        findViewById(R.id.add_friend_ll_invite_from_contact).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_friend_tv_search_friend:
                toSearchFriend();
                break;
//            case R.id.add_friend_tv_my_qrcode:
//                showMyQRCode();
//                break;
//            case R.id.add_friend_ll_add_from_contact:
//                addFriendFromContact();
//                break;
//            case R.id.add_friend_ll_scan:
//                toScanQRCode();
//                break;
//            case R.id.add_friend_ll_add_from_wechat:
//                inviteWechatFriend();
//                break;
//            case R.id.add_friend_ll_invite_from_contact:
//                inviteFromContact();
//                break;
        }
    }

    /**
     * 通过手机号/SealTalk号查找好友
     */
    private void toSearchFriend() {
        Intent intent = new Intent(this, SearchFriendActivity.class);
        startActivity(intent);
    }

//    /**
//     * 显示我的二维码
//     */
//    private void showMyQRCode() {
//        Intent qrCodeIntent = new Intent(this, QrCodeDisplayWindowActivity.class);
//        qrCodeIntent.putExtra(IntentExtra.STR_TARGET_ID, RongIM.getInstance().getCurrentUserId());
//        qrCodeIntent.putExtra(IntentExtra.SERIA_QRCODE_DISPLAY_TYPE, QrCodeDisplayType.PRIVATE);
//        startActivity(qrCodeIntent);
//    }
//
//    /**
//     * 从通讯录添加好友
//     */
//    private void addFriendFromContact() {
//        boolean hasPermissions = CheckPermissionUtils.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_PERMISSION_ADD_CONTACT_FREIND);
//        if (hasPermissions) {
//            Intent intent = new Intent(this, AddFriendFromContactActivity.class);
//            startActivity(intent);
//        }
//    }
//
//    /**
//     * 扫一扫
//     */
//    private void toScanQRCode() {
//        Intent intent = new Intent(this, ScanActivity.class);
//        startActivity(intent);
//    }
//
//    /**
//     * 微信邀请好友
//     */
//    private void inviteWechatFriend() {
//        CommonDialog commonDialog = new CommonDialog.Builder()
//                .setTitleText(R.string.new_friend_invite_wechat_friend)
//                .setContentMessage(getString(R.string.new_friend_invite_wechat_friend_dialog_content))
//                .setDialogButtonClickListener(new CommonDialog.OnDialogButtonClickListener() {
//                    @Override
//                    public void onPositiveClick(View v, Bundle bundle) {
//                        WXManager.getInstance().inviteToSealTalk();
//                    }
//
//                    @Override
//                    public void onNegativeClick(View v, Bundle bundle) {
//                    }
//                }).build();
//        commonDialog.show(getSupportFragmentManager(), null);
//    }
//
//    /**
//     * 从通讯录邀请好友
//     */
//    private void inviteFromContact() {
//        boolean hasPermissions = CheckPermissionUtils.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_PERMISSION_INVITE_CONTACT_FRIEND);
//        if (hasPermissions) {
//            Intent intent = new Intent(this, InviteFriendFromContactActivity.class);
//            startActivity(intent);
//        }
//    }
//
//    /**
//     * 跳转到请求通讯录说明界面
//     */
//    private void toRequestContactPermission() {
//        Intent intent = new Intent(this, RequestContactPermissionActivity.class);
//        startActivity(intent);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == REQUEST_PERMISSION_ADD_CONTACT_FREIND
//                || requestCode == REQUEST_PERMISSION_INVITE_CONTACT_FRIEND) {
//            boolean grandResult = true;
//            int length = permissions.length;
//            for (int i = 0; i < length; i++) {
//                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
//                    grandResult = false;
//                    break;
//                }
//            }
//            if (grandResult) {
//                if (requestCode == REQUEST_PERMISSION_ADD_CONTACT_FREIND) {
//                    addFriendFromContact();
//                } else if (requestCode == REQUEST_PERMISSION_INVITE_CONTACT_FRIEND) {
//                    inviteFromContact();
//                }
//            } else {
//                toRequestContactPermission();
//            }
//        }
//    }

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
}
