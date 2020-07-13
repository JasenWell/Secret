package com.xyp.mimi.mvp.ui.activity.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import com.github.customview.MyEditText;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.user.DaggerUserComponent;
import com.xyp.mimi.di.module.user.UserModule;
import com.xyp.mimi.mvp.contract.user.UserContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.user.UserRegisterPost;
import com.xyp.mimi.mvp.presenter.user.RegisterPresenter;
import com.xyp.mimi.mvp.utils.GetSign;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseSupportActivity<RegisterPresenter> implements UserContract.RegisterView {

    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.app_right_iv)
    ImageView appRightIv;
    @BindView(R.id.app_right_tv)
    TextView appRightTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.v_bottom_line)
    View vBottomLine;
    @BindView(R.id.et_phone)
    MyEditText etPhone;

    @BindView(R.id.et_password)
    MyEditText etPassword;
    @BindView(R.id.iv_password)
    ImageView ivPassword;
    @BindView(R.id.et_password2)
    MyEditText etPassword2;
    @BindView(R.id.iv_password1)
    ImageView ivPassword1;

    @BindView(R.id.tv_xieyi)
    TextView tvXieyi;

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
        DaggerUserComponent.builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this))
                .build().injectRegister(RegisterActivity.this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_register;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setAppTitle("注册");
    }

    @OnClick({R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                showMessage("密码不能为空/不可用");
                DeviceUtils.hideSoftKeyboard(mContext, getCurrentFocus());
                String phone = etPhone.getText().toString();
                String password = etPassword.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    showMessage("密码不能为空/不可用");
                    return;
                }
                if (!GetSign.isMobile((phone))) {
                    showMessage("请输入正确手机号");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    showMessage("账号不能为空");
                    return;
                }
                if (mPresenter != null) {
                    mPresenter.register(phone, password);
                }
                break;
        }
    }


    @Override
    public void registerCodeResult(BaseResponse baseResponse) {

    }

    @Override
    public void registerResult(BaseResponse baseResponse) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        showLoadSuccess();
        ArmsUtils.snackbarText(message);
    }


}
