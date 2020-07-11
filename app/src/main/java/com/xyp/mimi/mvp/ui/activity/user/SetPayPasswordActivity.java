package com.xyp.mimi.mvp.ui.activity.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
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
import com.xyp.mimi.mvp.http.entity.user.UserCodePost;
import com.xyp.mimi.mvp.http.entity.user.UserPayPasswordPost;
import com.xyp.mimi.mvp.presenter.user.PayPasswordPresenter;
import com.xyp.mimi.mvp.utils.AppConstant;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class SetPayPasswordActivity extends BaseSupportActivity<PayPasswordPresenter> implements UserContract.PayPasswordView {

    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.et_phone)
    MyEditText etPhone;
    @BindView(R.id.et_code)
    MyEditText etCode;
    @BindView(R.id.tv_getcode)
    MyTextView tvGetcode;
    @BindView(R.id.et_password)
    MyEditText etPassword;
    @BindView(R.id.et_password2)
    MyEditText etPassword2;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    String userId;
    String token;


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
                .injectChangePayPassword(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_change_pay_password; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setAppTitle("设置支付密码");
        userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);
    }


    @OnClick({R.id.tv_getcode,R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_getcode:
                DeviceUtils.hideSoftKeyboard(mContext, getCurrentFocus());
                getCode();
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
                    mPresenter.setPayPassword(new UserPayPasswordPost(userId,token,phone,password,code));
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
        mPresenter.getCode(new UserCodePost(userId,token,phone,1));
        countDown();
    }


    private void countDown() {
        tvGetcode.setEnabled(false);
        final long count = 60;
        Subscription subscribe = Observable.interval(1, TimeUnit.SECONDS)
                .take(61)//计时次数
                .map(integer -> count - integer)
                .compose(RxUtils.appSchedulers())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        tvGetcode.setEnabled(true);
                        tvGetcode.setText("获取验证码");
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onNext(Long aLong) {
                        tvGetcode.setText("剩下" + aLong + "s");
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
    public void PayPasswordCodeResult(BaseResponse baseResponse) {
        showLoadSuccess();
        ArmsUtils.snackbarText(baseResponse.getMsg());
    }

    @Override
    public void PayPasswordResult(BaseResponse baseResponse) {
        showLoadSuccess();
        ArmsUtils.snackbarText(baseResponse.getMsg());
    }
}
