package com.xyp.mimi.mvp.ui.activity.order;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.github.baseclass.BaseDividerListItem;
import com.github.baseclass.view.pickerview.pic.DensityUtil;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.contract.order.OrderDetailContract;
import com.xyp.mimi.mvp.http.Constant;
import com.xyp.mimi.mvp.http.entity.order.OrderDetailPost;
import com.xyp.mimi.mvp.http.entity.order.OrderDetailResult;
import com.xyp.mimi.mvp.presenter.order.OrderDetailPresenter;
import com.xyp.mimi.mvp.ui.adapter.order.OrderDetailListAdapter;
import com.xyp.mimi.mvp.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//订单详情
public class OrderDetailActivity extends BaseSupportActivity<OrderDetailPresenter> implements OrderDetailContract.View {

    @BindView(R.id.backdrop)
    ImageView backdrop;
    @BindView(R.id.ll_information)
    LinearLayout llInformation;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    @BindView(R.id.rv_order_goods)
    RecyclerView rvOrderGoods;

    OrderDetailListAdapter orderDetailListAdapter;

    List<OrderDetailResult.DataBean.OrderDetailsBean> data = new ArrayList<>();
    @BindView(R.id.order_number)
    TextView orderNumber;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_pay_time)
    TextView tvPayTime;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.nest)
    NestedScrollView nest;
    private String userId;
    private String token;
    private String OrderNo;

    int minDistance;
    int deltaDistance;

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }
    private State mCurrentState = State.IDLE;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .addTag("PicAndColor")
                .init();
    }


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
//        DaggerOrderDetailComponent.builder()
//                .appComponent(appComponent)
//                .view(this)
//                .build()
//                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {

        return R.layout.activity_order_detail1;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        OrderNo = getIntent().getStringExtra(Constant.OrderNo);//订单号
        userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);

        //距离最小值
        minDistance = DensityUtil.dip2px(OrderDetailActivity.this, 85);
        //距离范围差值
        deltaDistance = DensityUtil.dip2px(OrderDetailActivity.this, 200) - minDistance;

        orderDetailListAdapter = new OrderDetailListAdapter(data);
        rvOrderGoods.setLayoutManager(new LinearLayoutManager(mContext));
        rvOrderGoods.addItemDecoration(new BaseDividerListItem(mContext, BaseDividerListItem.VERTICAL_LIST, ConvertUtils.dp2px(1), R.color.white));
        rvOrderGoods.setAdapter(orderDetailListAdapter);
        mPresenter.getOrderDetail(new OrderDetailPost(userId, token, OrderNo));

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

                Log.d("展示",i+"---"+appBarLayout.getTotalScrollRange());
                if(Math.abs(i)>170){
                    llInformation.setTranslationX(255);
                    toolbar.getBackground().setAlpha(255);
                    ImmersionBar.with(OrderDetailActivity.this)
                            .statusBarColor(R.color.white)
//                            .statusBarDarkFont(true)
                            .init();
                    toolbarTitle.setTextColor(getResources().getColor(R.color.black));
                    toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.icon_back_black));
                }else{
                    toolbar.getBackground().setAlpha(Math.abs(i));
                    llInformation.setTranslationX(Math.abs(i));
                    toolbarTitle.setTextColor(getResources().getColor(R.color.white));
                    ImmersionBar.with(OrderDetailActivity.this).getTag("PicAndColor").init();
                    toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.icon_white_back));
                }

                if (i == 0) {
                    if (mCurrentState != State.EXPANDED) {
                        //展开
//                        onStateChanged(appBarLayout, State.EXPANDED);

                        toolbarTitle.setText("订单详情");
                        toolbar.getBackground().setAlpha(0);
                        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.icon_white_back));
                        //展开状态
                        toolbarTitle.setTextColor(getResources().getColor(R.color.white));

                    }
                    mCurrentState = State.EXPANDED;
                } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
                    if (mCurrentState != State.COLLAPSED) {//   //折叠状态
//                        onStateChanged(appBarLayout,State.COLLAPSED);
                        Log.d("折叠状态",i+"");
                        toolbarTitle.setText("待付款");
                        toolbar.getBackground().setAlpha(255);
                        toolbarTitle.setTextColor(getResources().getColor(R.color.black));

                    }
                    mCurrentState = State.COLLAPSED;
                } else {
                    if (mCurrentState != State.IDLE) { //中间状态
//                        onStateChanged(appBarLayout,State.IDLE);
                        Log.d("中间状态",i+"");
                    }
                    mCurrentState = State.IDLE;
                }


            }
        });
//        appbar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
//            @Override
//            public void onStateChanged(AppBarLayout appBarLayout, State state) {
//                if (state == State.EXPANDED) {
//                    toolbarTitle.setText("订单详情");
//                    toolbar.getBackground().setAlpha(0);
//                    //展开状态
//                    toolbarTitle.setTextColor(getResources().getColor(R.color.white));
////                  3
////                                   card.setVisibility(View.VISIBLE);
//                } else if (state == State.COLLAPSED) {
//                    toolbarTitle.setText("待付款");
//                    toolbar.getBackground().setAlpha(255);
//                    toolbarTitle.setTextColor(getResources().getColor(R.color.black));
////                    card.setVisibility(View.GONE);
//                    //折叠状态
////                    toolbar.setBackgroundColor(getResources().getColor(R.color.white));
//                } else {
//                    toolbar.getBackground().setAlpha(100);
//                    //中间状态
//
//                }
//            }
//        });


    }


    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void getOrderDetailResult(OrderDetailResult orderDetailResult) {
        showLoadSuccess();
        data.clear();
        if (orderDetailResult.getData().getOrderDetails().size() > 0) {
            data.addAll(orderDetailResult.getData().getOrderDetails());
            orderDetailListAdapter.notifyDataSetChanged();
        }
        orderNumber.setText(orderDetailResult.getData().getOrderNumber());
        tvCreateTime.setText(orderDetailResult.getData().getOrderTime());
        tvPayTime.setText(orderDetailResult.getData().getPaytime());
//        tvCode.setText(orderDetailResult.getData().getc);
    }


}
