package com.xyp.mimi.mvp.ui.activity.wallet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
//钱包
public class WalletActivity extends BaseSupportActivity {

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

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_wallet;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        appTitle.setText("我的钱包");
    }

    @OnClick({R.id.ll_mingxi,R.id.ll_yinhangka,R.id.tv_chongzhi,R.id.tv_tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_mingxi:
                STActivity(MoneyDetailActivity.class);
                break;
            case R.id.ll_yinhangka:
                STActivity(BankListActivity.class);
                break;
            case R.id.tv_chongzhi:
                STActivity(YueChongActivity.class);
                break;
            case R.id.tv_tixian:
                STActivity(TixianActivity.class);
                break;
        }
    }
}
