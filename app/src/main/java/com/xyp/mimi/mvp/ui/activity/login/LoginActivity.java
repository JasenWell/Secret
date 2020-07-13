package com.xyp.mimi.mvp.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.github.customview.MyEditText;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseApp;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.user.DaggerUserComponent;
import com.xyp.mimi.di.module.user.UserModule;
import com.xyp.mimi.mvp.contract.user.UserContract;
import com.xyp.mimi.mvp.http.entity.login.LoginUserResult;
import com.xyp.mimi.mvp.http.entity.login.LoginUserPost;
import com.xyp.mimi.mvp.presenter.user.LoginPresenter;
import com.xyp.mimi.mvp.ui.activity.MainActivity;
import com.xyp.mimi.mvp.ui.activity.user.RetrievePasswordActivity;
import com.xyp.mimi.mvp.utils.AppConstant;

import butterknife.BindView;
import butterknife.OnClick;
public class LoginActivity extends BaseSupportActivity<LoginPresenter> implements UserContract.LoginView {


    @BindView(R.id.et_login_phone)
    MyEditText etLoginPhone;
    @BindView(R.id.et_login_password)
    MyEditText etLoginPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;

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
                .build()
                .injectLogin(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        etLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏  默认

        etLoginPassword.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                //EditText输入状态改变，Button背景颜色也改变
                if ("".equals(etLoginPassword.getText().toString().trim())) {
                    tvLogin.setEnabled(false);
                    tvLogin.setBackground(getResources().getDrawable(R.drawable.shape_grey));
                } else {
                    tvLogin.setEnabled(true);
                    tvLogin.setBackground(getResources().getDrawable(R.drawable.shape));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (temp.length() >= 8) {//输入字数限制
//                    tvRegister.setSolidColor(R.color.bottom_color);
//                }
            }
        });
    }

    @OnClick({R.id.tv_login,R.id.tv_register,R.id.tv_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                attemptLogin();
                break;
            case R.id.tv_register:
                STActivity(RegisterActivity.class);
                break;
            case R.id.tv_forget_password:
                STActivity(RetrievePasswordActivity.class);
                break;

        }
    }

    private void attemptLogin() {
        DeviceUtils.hideSoftKeyboard(mContext, getCurrentFocus());
        String phone = etLoginPhone.getText().toString();
        String password = etLoginPassword.getText().toString();

        if (TextUtils.isEmpty(password)) {
            showMessage("密码不能为空/不可用");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            showMessage("账号不能为空");
            return;
        }

        if (mPresenter != null) {
            mPresenter.login(phone, password);
        }
    }

    @Override
    public void showMessage(@NonNull String message) {
        showLoadSuccess();
        ArmsUtils.snackbarText(message);
    }


    @Override
    public void loginResult(LoginUserResult loginUserResult) {
        showLoadSuccess();
        SPUtils.getInstance().put(AppConstant.User.USER_ID, loginUserResult.getUserId());//
        SPUtils.getInstance().put(AppConstant.User.TOKEN, loginUserResult.getToken());//
        SPUtils.getInstance().put(AppConstant.User.AVATAR, loginUserResult.getAvatar());
        SPUtils.getInstance().put(AppConstant.User.NICK_NAME, loginUserResult.getNickName());
        SPUtils.getInstance().put(AppConstant.User.REFERRAL_CODE, loginUserResult.getReferralCode());
        startActivity(new Intent(mContext, MainActivity.class));
        finish();
    }
}
