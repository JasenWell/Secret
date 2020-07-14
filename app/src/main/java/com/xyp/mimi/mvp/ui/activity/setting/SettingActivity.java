package com.xyp.mimi.mvp.ui.activity.setting;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.github.baseclass.view.MyDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.setting.DaggerSettingComponent;
import com.xyp.mimi.di.module.setting.SettingModule;
import com.xyp.mimi.mvp.contract.setting.SettingContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.setting.UserPasswordResult;
import com.xyp.mimi.mvp.presenter.setting.SettingPresenter;
import com.xyp.mimi.mvp.ui.activity.login.LoginActivity;
import com.xyp.mimi.mvp.ui.activity.user.ChangeLoginPasswordActivity;
import com.xyp.mimi.mvp.ui.activity.user.SetPayPasswordActivity;
import com.xyp.mimi.mvp.utils.AppConstant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.OnClick;

public class SettingActivity  extends BaseSupportActivity <SettingPresenter> implements SettingContract.View {



    private  String phone;
    private String uId;
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
        DaggerSettingComponent.builder()
                .appComponent(appComponent)
                .settingModule(new SettingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_setting;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setAppTitle("设置");
        uId =  SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        phone =  SPUtils.getInstance().getString(AppConstant.User.PHONE);

    }



    @OnClick({R.id.ll_loginpassword,R.id.ll_paypassword,R.id.tv_setting_exit,R.id.ly_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ly_user:
                mPresenter.getUserPassword(phone);
                break;
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
                        mPresenter.userLoginOut(uId);
                        exitLogin();
                    }
                });
                mDialog.create().show();

                break;
        }
    }

    private void exitLogin() {

    }

    @Override
    public void getUserPasswordResult(UserPasswordResult s) {
          showToast("账号："+s.getData().getAccount()+",密码"+s.getData().getPassword());
         showLoadSuccess();
    }

    @Override
    public void userLoginOutResult(BaseResponse s) {
        SPUtils.getInstance().remove(AppConstant.User.USER_ID);
        SPUtils.getInstance().remove(AppConstant.User.TOKEN);
        SPUtils.getInstance().remove(AppConstant.User.AVATAR);
        SPUtils.getInstance().remove(AppConstant.User.NICK_NAME);
        SPUtils.getInstance().remove(AppConstant.User.REFERRAL_CODE);
        ActivityUtils.finishAllActivities();
        STActivity(LoginActivity.class);
        finish();
    }

    @Override
    public void showMessage(@NonNull String message) {
        ArmsUtils.snackbarText(message);
    }
}
