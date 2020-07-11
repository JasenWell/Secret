package com.xyp.mimi.mvp.ui.activity.setting;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.github.baseclass.view.MyDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.ui.activity.login.LoginActivity;
import com.xyp.mimi.mvp.ui.activity.user.ChangeLoginPasswordActivity;
import com.xyp.mimi.mvp.ui.activity.user.SetPayPasswordActivity;
import com.xyp.mimi.mvp.utils.AppConstant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.OnClick;

public class SettingActivity  extends BaseSupportActivity {




    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .init();
    }


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_setting;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setAppTitle("设置");

    }



    @OnClick({R.id.ll_loginpassword,R.id.ll_paypassword,R.id.tv_setting_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_loginpassword:
                STActivity(ChangeLoginPasswordActivity.class);
                break;
            case R.id.ll_paypassword:
                STActivity(SetPayPasswordActivity.class);
                break;

            case R.id.tv_setting_exit:
                MyDialog.Builder mDialog = new MyDialog.Builder(mContext);
                mDialog.setMessage("是否确认退出登录?");
                mDialog.setNegativeButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        exitLogin();
                    }
                });
                mDialog.create().show();

                break;
        }
    }

    private void exitLogin() {

        SPUtils.getInstance().remove(AppConstant.User.USER_ID);
        SPUtils.getInstance().remove(AppConstant.User.TOKEN);
        SPUtils.getInstance().remove(AppConstant.User.AVATAR);
        SPUtils.getInstance().remove(AppConstant.User.NICK_NAME);
        SPUtils.getInstance().remove(AppConstant.User.REFERRAL_CODE);
        ActivityUtils.finishAllActivities();
        STActivity(LoginActivity.class);
        finish();
    }
}
