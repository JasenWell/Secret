package com.xyp.mimi.mvp.ui.activity.circle;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.github.baseclass.rx.RxBus;
import com.github.baseclass.view.MyDialog;
import com.github.customview.MyEditText;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.circle.DaggerCircleComponent;
import com.xyp.mimi.di.module.circle.CircleModule;
import com.xyp.mimi.mvp.contract.circle.CircleContract;
import com.xyp.mimi.mvp.event.RefreshAddressEvent;
import com.xyp.mimi.mvp.event.RefreshCircleEvent;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.circle.CirclePost;
import com.xyp.mimi.mvp.presenter.circle.CirclePushPresenter;
import com.xyp.mimi.mvp.utils.AppConstant;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddCircleActivity extends BaseSupportActivity<CirclePushPresenter> implements CircleContract.PushCircleView {

    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.app_right_iv)
    ImageView appRightIv;
    @BindView(R.id.app_right_tv)
    TextView appRightTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.v_bottom_line)
    View vBottomLine;
    @BindView(R.id.et_content)
    MyEditText etContent;

    private String uId;
    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarColor(R.color.white)
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
        return R.layout.activity_circle_add; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        uId =  SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        setAppTitle("发布文字");
        appRightTv.setText("发布");
    }

    @OnClick({R.id.app_right_tv})
    protected void onViewClick(View v) {
        switch (v.getId()) {

            case R.id.app_right_tv:
                if (TextUtils.isEmpty(getSStr(etContent).trim())) {
                    showMessage("发布内容不能为空");
                    return;
                }
                //                传入 circleName 圈子主题名称
//                传入 content    圈子内容
                MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
                mDialog.setMessage("是否发布新动态");
                mDialog.setNegativeButton("取消",(dialog, which) -> dialog.dismiss());
                mDialog.setPositiveButton("确定",(dialog, which) -> {
                    mPresenter.pushCircle(uId,etContent.getText().toString());
                    dialog.dismiss();
                });
                mDialog.create().show();
                break;
        }
    }
    @Override
    public void pushCircleResult(BaseResponse baseResponse) {
        showLoadSuccess();
        EventBus.getDefault().post(new RefreshCircleEvent());
        finish();
    }

    @Override
    public void showMessage(@NonNull String message) {
        showLoadSuccess();
        ArmsUtils.snackbarText(message);
    }

}
