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

public class UserActivity extends BaseSupportActivity {


    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.et_acc)
    MyEditText etAcc;
    @BindView(R.id.et_number)
    MyEditText etNumber;
    @BindView(R.id.et_endtime)
    MyEditText etEndtime;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_user;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        appTitle.setText("个人中心");


    }


}
