package com.xyp.mimi.mvp.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.http.api.Api;
import com.xyp.mimi.mvp.ui.activity.MainActivity;
import com.xyp.mimi.mvp.utils.AppConstant;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

public class SplashActivity extends BaseSupportActivity  {


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        RetrofitUrlManager.getInstance().setGlobalDomain(Api.APP_DOMAIN);

        new Handler().postDelayed(this::enterHomeActivity, 1000);

//        RetrofitUrlManager.getInstance().setGlobalDomain(Api.APP_CHECK);//获取check
//        mPresenter.getCheck();
//        // 判断是否是第一次开启应用
//        boolean isFirstOpen = (boolean) SpUtils.get(this, AppConstant.FIRST_OPEN, false);
        // 如果是第一次启动，则先进入功能引导页
//        if (!isFirstOpen) {
//            startActivity(new Intent(this, WelcomeActivity.class));
//            finish();
//            return;
//        }

        // 如果不是第一次启动app，则正常显示启动屏

    }

    private void enterHomeActivity() {
        String token =  SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        if (!TextUtils.isEmpty(token)) {
            startActivity(new Intent(mContext, com.xyp.mimi.MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(mContext, LoginActivity.class));//LoginAcitivity
            finish();
        }
    }

//    @Override
//    public void getCheckSuccess(Check check) {
//        if(check.getMarket().equals("0")){
//            startActivity(new Intent(mContext, com.yiwu.ddyp.djzhao.smarttool.activity.MainActivity.class));
//            finish();
//        }else{
//            new Handler().postDelayed(this::enterHomeActivity, 1000);
//        }
//    }
//
//    @Override
//    public void showMessage(@NonNull String message) {
//
//    }
}