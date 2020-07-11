package com.xyp.mimi.mvp.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.customview.MyEditText;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;

import butterknife.BindView;

public class GoogleMoneyResultActivity extends BaseSupportActivity {


    String money;
    @BindView(R.id.et_acc)
    MyEditText etAcc;


    @BindView(R.id.app_title)
    TextView appTitle;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_google_money;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        appTitle.setText("余额");
        money = getIntent().getStringExtra("money");
        etAcc.setText(money);
    }

}
