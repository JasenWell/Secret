package com.xyp.mimi.mvp.ui.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.user.DaggerUserComponent;
import com.xyp.mimi.di.module.user.UserModule;
import com.xyp.mimi.mvp.contract.user.UserContract;
import com.xyp.mimi.mvp.event.RefreshUserInfoEvent;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.user.UserEditPost;
import com.xyp.mimi.mvp.presenter.user.UserNamePresenter;
import com.xyp.mimi.mvp.utils.AppConstant;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class UserNameActivity extends BaseSupportActivity<UserNamePresenter> implements UserContract.UserNameEditView {


    @BindView(R.id.app_title)
    TextView appTitle;

    @BindView(R.id.et_edit_name)
    MyEditText etEditName;

    @BindView(R.id.tv_save)
    MyTextView tvSave;

    String nickName;

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
        DaggerUserComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this))
                .build()
                .injectUserName(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_username_edit;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setAppTitle("修改昵称");
        nickName = getIntent().getStringExtra("NICKNAME");
        etEditName.setText(nickName);
        userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);

    }


    @OnClick({R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                String nickName = etEditName.getText().toString();
                if (TextUtils.isEmpty(nickName)) {
                    showMessage("昵称不能为空");
                    return;
                }
                mPresenter.editName(new UserEditPost(userId,token,nickName));
                break;
        }
    }


    @Override
    public void editResult(BaseResponse baseResponse) {
        showLoadSuccess();
        finish();
        EventBus.getDefault().post(new RefreshUserInfoEvent());

    }

    @Override
    public void showMessage(@NonNull String message) {
        showLoadSuccess();
        ArmsUtils.snackbarText(message);
    }

}
