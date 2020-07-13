package com.xyp.mimi.mvp.ui.activity.circle;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.contract.circle.CircleContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.presenter.circle.CirclePushPresenter;

public class AddCircleActivity extends BaseSupportActivity<CirclePushPresenter>implements CircleContract.PushCircleView {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_circle; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void pushCircleResult(BaseResponse baseResponse) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
