package com.xyp.mimi.mvp.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;

public class MemberActivity extends BaseSupportActivity {
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_user;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
