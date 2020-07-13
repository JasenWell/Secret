package com.xyp.mimi.mvp.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.contract.PasswordContract;
import com.xyp.mimi.mvp.presenter.PasswordPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class PasswordActivity extends BaseSupportActivity<PasswordPresenter> implements PasswordContract.View {

//    @BindView(R.id.et_old_password)
//    MyEditText etOldPassword;
//
//    @BindView(R.id.et_impwd)
//    MyEditText etImpwd;
//
//    @BindView(R.id.et_pwd2)
//    MyEditText etPwd2;

    //    @BindView(R.id.btn_login_forget)
//    TextView btnLoginForget;
//    @BindView(R.id.btn_login)
//    Button btnLogin;
//    @BindView(R.id.btn_register)
//    TextView btnRegister;

    @BindView(R.id.app_title)
    TextView appTitle;

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
//        DaggerPasswordComponent.builder()
//                .appComponent(appComponent)
//                .passwordModule(new PasswordModule(this))
//                .build()
//                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_password;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        appTitle.setText("找回密码");
    }

    @OnClick({R.id.btn_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {


            case R.id.btn_change:
                changePassword();
                break;
//            case R.id.btn_register:
//                startActivity(new Intent(mContext, RegisterActivity.class));
//                break;
        }
    }

    private void  changePassword() {
//        String oldPassword = etOldPassword.getText().toString();
//        String password = etImpwd.getText().toString();
//        String password2 = etPwd2.getText().toString();
//
//        if (TextUtils.isEmpty(oldPassword)) {
//            showMessage("原密码不能为空");
//            return;
//        }
//        if (TextUtils.isEmpty(password)) {
//            showMessage("密码不能为空/不可用");
//            return;
//        }
//        if (!password.equals(password2)) {
//            showMessage("2次密码不一致");
//            return;
//        }
//        if (mPresenter != null) {
//            DeviceUtils.hideSoftKeyboard(mContext, getCurrentFocus());
//            mPresenter.changePassword( SPUtils.getInstance().getString(AppConstant.User.USER_ID),oldPassword,password);
//        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }




    @Override
    public void showMessage(@NonNull String message) {
        showLoadSuccess();
        ArmsUtils.snackbarText(message);
    }



    @Override
    public void changePasswordSuccess(String s) {
        showLoadSuccess();
        showMessage("修改成功");
//        etOldPassword.setText("");
//        etImpwd.setText("");
//        etPwd2.setText("");
    }
}
