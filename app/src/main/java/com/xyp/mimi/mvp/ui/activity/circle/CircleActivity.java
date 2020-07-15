package com.xyp.mimi.mvp.ui.activity.circle;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.github.androidtools.PhoneUtils;
import com.github.baseclass.view.MyDialog;
import com.github.customview.MyImageView;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.circle.DaggerCircleComponent;
import com.xyp.mimi.di.module.circle.CircleModule;
import com.xyp.mimi.mvp.contract.circle.CircleContract;
import com.xyp.mimi.mvp.event.RefreshCircleEvent;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.circle.CircleListResult;
import com.xyp.mimi.mvp.presenter.circle.CirclePresenter;
import com.xyp.mimi.mvp.ui.adapter.circle.CircleAdapter;
import com.xyp.mimi.mvp.utils.AppConstant;
import com.xyp.mimi.mvp.utils.GlideLoadUtils;
import com.xyp.mimi.mvp.view.DividerGridItemDecoration;
import com.xyp.mimi.mvp.view.UniversalItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class CircleActivity extends BaseSupportActivity<CirclePresenter> implements CircleContract.CircleListView {


    @BindView(R.id.rv_circle)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
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

    private String uId;


    private CircleAdapter adapter;

    private String imgurl;
    private List<CircleListResult.DataBean.DlistBean> data = new ArrayList<>(); //圈子列表的数据


    @SuppressLint("TimberArgCount")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshCircleEvent event) {
        Timber.e("Activity", "11111");
        getData();
    }


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
        return R.layout.activity_circle; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setAppTitle("朋友圈");
        uId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        imgurl =  SPUtils.getInstance().getString(AppConstant.User.AVATAR);
        appRightTv.setText("发布");
        mPresenter.getCircleList(uId);

        adapter = new CircleAdapter(data);
        LinearLayoutManager xLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.addItemDecoration(new UniversalItemDecoration() {
            @Override
            public Decoration getItemOffsets(int position) {
                ColorDecoration decoration = new ColorDecoration();
                if (position == 0) {
                    decoration.bottom = 20;
                    decoration.decorationColor = getResources().getColor(R.color.white);
                }else {
                    decoration.bottom =5 ;
                    decoration.decorationColor = getResources().getColor(R.color.divider_color);
                }
                return decoration;
            }
        });

        recyclerView.setLayoutManager(xLinearLayoutManager);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getData();
            }
        });
        adapter.setOnCircleDeleteListener(new CircleAdapter.OnClickCircleDeleteListener() {
            @Override
            public void deleteCircle(String id) {
                MyDialog.Builder mDialog = new MyDialog.Builder(mContext);
                mDialog.setMessage("是否确认删除?");
                mDialog.setNegativeButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mPresenter.deleteCircle(id);
                    }
                });
                mDialog.create().show();
            }
        });
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshlayout) {
//                startIndex += 10;
//                getLoadMoreData();  //上拉加载添加数据
//            }
//        });


    }

    private void getData() {
        mPresenter.getCircleList(uId);
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

    @OnClick({R.id.app_right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.app_right_tv:
                STActivityForResult(AddCircleActivity.class, 100);
                break;
        }
    }


    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public void getCircleListResult(CircleListResult s) {
        showLoadSuccess();
        data.clear();
        adapter.removeAllHeaderView();
        if (s.getData().getDlist().size() > 0) {
            data.addAll(s.getData().getDlist());
            //把广告数据填充到圈子一级界面
            adapter.notifyDataSetChanged();

            refreshLayout.finishRefresh();
            refreshLayout.setNoMoreData(false);
            adapter.addHeaderView(addHeader());
        } else {
//            View emptyView = getLayoutInflater().inflate(R.layout.empty_no_record, null);
//            emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT));
//            adapter.setEmptyView(emptyView);
            adapter.addHeaderView(addHeader());
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void getDeleteCircleResult(BaseResponse s) {
        showLoadSuccess();
        EventBus.getDefault().post(new RefreshCircleEvent());
    }

    @SuppressLint({"SetTextI18n", "TimberArgCount"})
    public View addHeader() {
        Timber.e("Activity", imgurl);
        View view = View.inflate(mContext, R.layout.item_circle_head, null);
        ImageView big = view.findViewById(R.id.tv_big_pic);

        GlideLoadUtils.getInstance().glideLoad(mContext,imgurl,big,R.drawable.default_image);
        MyImageView small = view.findViewById(R.id.tv_small_pic);
        GlideLoadUtils.getInstance().glideLoad(mContext,imgurl,small,R.drawable.default_image);
        return view;
    }



}
