package com.xyp.mimi.mvp.ui.activity.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseApp;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.user.DaggerUserComponent;
import com.xyp.mimi.di.module.user.UserModule;
import com.xyp.mimi.im.bean.HashKit;
import com.xyp.mimi.im.bean.ResponseIMTokenInfo;
import com.xyp.mimi.im.bean.ResponseUserInfo;
import com.xyp.mimi.im.common.ResultCallback;
import com.xyp.mimi.im.im.IMManager;
import com.xyp.mimi.im.model.UserCacheInfo;
import com.xyp.mimi.im.net.hjh.OkHttpUtils;
import com.xyp.mimi.im.sp.UserCache;
import com.xyp.mimi.mvp.contract.user.UserContract;
import com.xyp.mimi.mvp.http.entity.login.LoginUserResult;
import com.xyp.mimi.mvp.http.entity.login.LoginUserPost;
import com.xyp.mimi.mvp.presenter.user.LoginPresenter;
import com.xyp.mimi.mvp.ui.activity.MainActivity;
import com.xyp.mimi.mvp.ui.activity.user.RetrievePasswordActivity;
import com.xyp.mimi.mvp.utils.AppConstant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
        connectIM(loginUserResult);
    }

    private void switchPage(LoginUserResult loginUserResult){
        showLoadSuccess();


        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

            /**
             * 获取设置用户信息. 通过返回的 userId 来封装生产用户信息.
             * @param userId 用户 ID
             */
            @Override
            public UserInfo getUserInfo(String userId) {
                UserInfo userInfo = new UserInfo(userId, loginUserResult.getUser().getId(), Uri.parse(loginUserResult.getUser().getImgUrl()));
                return userInfo;
            }

        }, true);

        SPUtils.getInstance().put(AppConstant.User.USER_ID, loginUserResult.getUser().getId());//
        SPUtils.getInstance().put(AppConstant.User.PHONE, loginUserResult.getUser().getAccount());//
        SPUtils.getInstance().put(AppConstant.User.TOKEN, loginUserResult.getToken());//
        SPUtils.getInstance().put(AppConstant.User.AVATAR, loginUserResult.getUser().getImgUrl());
        SPUtils.getInstance().put(AppConstant.User.NICK_NAME, loginUserResult.getNickName());
        SPUtils.getInstance().put(AppConstant.User.REFERRAL_CODE, loginUserResult.getReferralCode());
        startActivity(new Intent(mContext, com.xyp.mimi.MainActivity.class));
        finish();
    }

    private void connectIM(LoginUserResult loginUserResult){
        final String token =  UserCache.getInstance().getString(UserCache.KEY_USER_TOKEN+loginUserResult.getUser().getId(),"");
        if(token == null || token.equals("")){
            getToken(loginUserResult);
        }else {
            connectIM(loginUserResult,token);
        }

    }

    private void connectIM(LoginUserResult loginUserResult,String token){
        IMManager.getInstance().connectIM(token, true, new ResultCallback<String>() {
            @Override
            public void onSuccess(String s) {
                // 存储当前登录成功的用户信息
                UserCacheInfo info = new UserCacheInfo(loginUserResult.getUser().getId(),token,
                        loginUserResult.getUser().getAccount(), loginUserResult.getUser().getPassword(), loginUserResult.getUser().getCountr());
                UserCache.getInstance().saveUserCache(info);
                switchPage(loginUserResult);
            }

            @Override
            public void onFail(int errorCode) {
            }
        });
    }


    public Map addTokenMap() {
        Map<String, String> map = new HashMap<String, String>();
        String key = "pvxdm17jpe5cr";
        String secret = "pbPDAAqPawQFq";
        String nonce = ""+System.currentTimeMillis();
        String timestamp = ""+System.currentTimeMillis();
        String  signature = HashKit.hexSHA1(secret+nonce+timestamp);
        map.put("RC-App-Key", key);
        map.put("RC-Nonce", nonce);
        map.put("RC-Timestamp",timestamp);
        map.put("RC-Signature",signature);
        return map;
    }

    private void getToken(LoginUserResult loginUserResult){
        Map<String,String> map = new HashMap<>();
        map.put("userId",loginUserResult.getUser().getId());
        map.put("name",loginUserResult.getUser().getUserName());
        map.put("portraitUri",loginUserResult.getUser().getImgUrl());
        OkHttpUtils.getInstance().post(map,"http://api-cn.ronghub.com/user/getToken.json",addTokenMap()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = null;
                try {
                    json = response.body().string();
                    if(json != null){
                        ResponseIMTokenInfo tokenInfo  = new Gson().fromJson(json,ResponseIMTokenInfo.class);
                        if(tokenInfo != null){
                            loginUserResult.setTokenInfo(tokenInfo);
                            UserCacheInfo info = new UserCacheInfo(loginUserResult.getUser().getId(),tokenInfo.getToken(),
                                    loginUserResult.getUser().getAccount(), loginUserResult.getUser().getPassword(), loginUserResult.getUser().getCountr());
                            UserCache.getInstance().saveUserCache(info);
                            Message message = handler.obtainMessage();
                            message.obj = loginUserResult;
                            message.what = 1901;
                            handler.sendMessage(message);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Handler handler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1901){
                LoginUserResult loginUserResult = (LoginUserResult) msg.obj;;
                ResponseIMTokenInfo tokenInfo = loginUserResult.getTokenInfo();
                UserCache.getInstance().putString(UserCache.KEY_USER_TOKEN+tokenInfo.getUserId(),tokenInfo.getToken());
                connectIM(loginUserResult,loginUserResult.getTokenInfo().getToken());
            }
        }
    };
}
