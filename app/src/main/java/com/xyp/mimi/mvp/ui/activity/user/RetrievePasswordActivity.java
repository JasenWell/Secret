package com.xyp.mimi.mvp.ui.activity.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.github.rxjava.rxbus.RxUtils;
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
import com.xyp.mimi.mvp.presenter.user.RetrievePasswordPresenter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

import static com.jess.arms.utils.Preconditions.checkNotNull;
public class RetrievePasswordActivity extends BaseSupportActivity<RetrievePasswordPresenter> implements UserContract.RetrievePasswordView {

    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.et_phone)
    MyEditText etPhone;
    @BindView(R.id.et_code)
    MyEditText etCode;
    @BindView(R.id.tv_register_getcode)
    MyTextView tvRegisterGetcode;
    @BindView(R.id.et_password)
    MyEditText etPassword;
    @BindView(R.id.et_password2)
    MyEditText etPassword2;
    @BindView(R.id.tv_commit)
    TextView tvCommit;



    boolean passwordEye, passwordEye1;

    @BindView(R.id.iv_password)
    ImageView ivPassword;
    @BindView(R.id.iv_password1)
    ImageView ivPassword1;

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
                .injectRetrievePassword(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_password; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setAppTitle("找回密码");
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏  默认
        etPassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏

        etPassword2.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                //EditText输入状态改变，Button背景颜色也改变
                if ("".equals(etPassword2.getText().toString().trim())) {
                    tvCommit.setEnabled(false);
                    tvCommit.setBackground(getResources().getDrawable(R.drawable.shape_grey));
                } else {
                    tvCommit.setBackground(getResources().getDrawable(R.drawable.shape));
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


    @OnClick({R.id.tv_register_getcode, R.id.iv_password,R.id.iv_password1,R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register_getcode:
                DeviceUtils.hideSoftKeyboard(mContext, getCurrentFocus());
                getCode();
                break;
            case R.id.iv_password:
                passwordEye = !passwordEye;
                if (passwordEye) {
                    ivPassword.setImageDrawable(getResources().getDrawable(R.mipmap.eye));
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//显示
                } else {
                    ivPassword.setImageDrawable(getResources().getDrawable(R.mipmap.close_eye));
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏
                }
                break;
            case R.id.iv_password1:
                passwordEye1 = !passwordEye1;
                if (passwordEye1) {
                    ivPassword1.setImageDrawable(getResources().getDrawable(R.mipmap.eye));
                    etPassword2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//显示
                } else {
                    ivPassword1.setImageDrawable(getResources().getDrawable(R.mipmap.close_eye));
                    etPassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏
                }
                break;
            case R.id.tv_commit:
                DeviceUtils.hideSoftKeyboard(mContext, getCurrentFocus());
                String phone = etPhone.getText().toString();
                String code = etCode.getText().toString();
                String password = etPassword.getText().toString();
                String password2 =etPassword2.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showMessage("手机号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    showMessage("验证码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(password)||TextUtils.isEmpty(password2)) {
                    showMessage("密码不能为空");
                    return;
                }
                if (!password.equals(password2)) {
                    showMessage("两次密码不一样");
                    return;
                }
                if (mPresenter != null) {
                    mPresenter.retrievePassword(new UserRegisterPost(phone,password));
                }
                break;


        }
    }

    private void getCode() {
        String phone = etPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            showMessage("手机号不能为空");
            return;
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile", phone);
        mPresenter.getRetrievePasswordCode(map);
        countDown();
    }


    private void countDown() {
        tvRegisterGetcode.setEnabled(false);
        final long count = 60;
        Subscription subscribe = Observable.interval(1, TimeUnit.SECONDS)
                .take(61)//计时次数
                .map(integer -> count - integer)
                .compose(RxUtils.appSchedulers())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        tvRegisterGetcode.setEnabled(true);
                        tvRegisterGetcode.setText("获取验证码");
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onNext(Long aLong) {
                        tvRegisterGetcode.setText("剩下" + aLong + "s");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
        addSubscription(subscribe);
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }


    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public void RetrievePasswordCodeResult(BaseResponse baseResponse) {
        showLoadSuccess();
        ArmsUtils.snackbarText(baseResponse.getMsg());
    }

    @Override
    public void RetrievePasswordResult(BaseResponse baseResponse) {
        showLoadSuccess();
        ArmsUtils.snackbarText(baseResponse.getMsg());
    }
}
