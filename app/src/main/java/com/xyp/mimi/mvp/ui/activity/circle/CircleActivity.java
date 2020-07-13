package com.xyp.mimi.mvp.ui.activity.circle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.circle.DaggerCircleComponent;
import com.xyp.mimi.di.module.circle.CircleModule;
import com.xyp.mimi.mvp.contract.circle.CircleContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.presenter.circle.CirclePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class CircleActivity extends BaseSupportActivity<CirclePresenter> implements CircleContract.CircleListView {


    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.app_right_iv)
    ImageView appRightIv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_circle)
    RecyclerView rvCircle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarColor(R.color.liji_c_blue)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCircleComponent.builder()
                .appComponent(appComponent)
                .circleModule(new CircleModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_circle; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setAppTitle("朋友圈");
        appRightIv.setBackgroundResource(R.drawable.jia);

    }

//    @Override
//    public void initData(@Nullable Bundle savedInstanceState) {
////         toolbar.setBackgroundColor(getResources().getColor(R.color.liji_c_blue));
//          appTitle.setText("朋友圈");
////        appRightIv.setBackgroundResource(R.drawable.jia);
////        appRightTv.setVisibility(View.VISIBLE);
//    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @OnClick({R.id.app_right_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.app_right_iv:
                STActivityForResult(AddCircleActivity.class, 100);
                break;
        }
    }


    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void getCircleListResult(BaseResponse s) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
