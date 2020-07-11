package com.xyp.mimi.mvp.ui.activity.wallet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.wallet.DaggerBankComponent;
import com.xyp.mimi.di.module.wallet.BankModule;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.wallet.MyEarningPost;
import com.xyp.mimi.mvp.http.entity.wallet.MyEarningResult;
import com.xyp.mimi.mvp.presenter.wallet.MyEarningPresenter;
import com.xyp.mimi.mvp.utils.AppConstant;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的收益
 */
public class MyEarningActivity extends BaseSupportActivity<MyEarningPresenter> implements BankContract.MyEarningView {


    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.tv_today_shouyi)
    TextView tvTodayShouyi;
    @BindView(R.id.tv_yestoday_shouyi)
    TextView tvYestodayShouyi;
    @BindView(R.id.tv_total_shouyi)
    TextView tvTotalShouyi;

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
        DaggerBankComponent.builder()
                .appComponent(appComponent)
                .bankModule(new BankModule(this))
                .build()
                .injectEarning(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_my_earning;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        appTitle.setText("我的收益");
        String userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        String token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);
        mPresenter.getMyEarning(new MyEarningPost(userId, token));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getMyEarningResult(BaseResponse<MyEarningResult> myEarningResult) {
        showLoadSuccess();
        tvTodayShouyi.setText(myEarningResult.getData().getJTAmount()+"元");
        tvYestodayShouyi.setText(myEarningResult.getData().getZTAmount()+"元");
        tvTotalShouyi.setText(myEarningResult.getData().getLJAmount()+"元");
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @OnClick({R.id.ll_mingxi,R.id.ll_tixianjilu})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.ll_mingxi:
                intent.putExtra("TYPE", "shouyimingxi");
                STActivity(intent,EarningAndTixianActivity.class);
                break;
            case R.id.ll_tixianjilu:
                intent.putExtra("TYPE", "tixianjilu");
                STActivity(intent,EarningAndTixianActivity.class);
                break;


        }
    }



}
