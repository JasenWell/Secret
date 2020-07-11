package com.xyp.mimi.mvp.ui.activity.wallet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.wallet.TixianPost;
import com.xyp.mimi.mvp.presenter.wallet.YueTixianPresenter;
import com.xyp.mimi.mvp.utils.AppConstant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.OnClick;

public class TixianActivity extends BaseSupportActivity <YueTixianPresenter> implements BankContract.MyYueTixianView {

    @BindView(R.id.app_title)
    TextView appTitle;
    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .keyboardEnable(false)
                .init();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
//        DaggerBankComponent.builder()
//                .appComponent(appComponent)
//                .bankModule(new BankModule(this))
//                .build()
//                .injectYueTixian(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_tixian;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        appTitle.setText("提现");

    }

    @Override
    public void getMyEarningResult(BaseResponse<Object> myEarningResult) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @OnClick({R.id.tv_tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_tixian:
                String userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
                String token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);
                mPresenter.yueTixian(new TixianPost(userId,token,"200","13"));
                break;

        }
    }


}
