package com.xyp.mimi.mvp.ui.activity.wallet;

import android.os.Bundle;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.BindView;

public class YueChongActivity extends BaseSupportActivity {

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

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_yue_chongzhi;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        appTitle.setText("余额充值");
    }
}
