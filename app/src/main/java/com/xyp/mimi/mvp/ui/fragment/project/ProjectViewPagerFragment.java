package com.xyp.mimi.mvp.ui.fragment.project;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.di.component.AppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportFragment;
import com.xyp.mimi.mvp.contract.project.ProjectContract;
import com.xyp.mimi.mvp.http.Constant;
import com.xyp.mimi.mvp.http.entity.order.OrderListResult;
import com.xyp.mimi.mvp.presenter.project.ProjectPresenter;
import com.xyp.mimi.mvp.ui.adapter.order.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ProjectViewPagerFragment extends BaseSupportFragment<ProjectPresenter> implements ProjectContract.View {


    @BindView(R.id.rv_order)
    RecyclerView rvOrder;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String userId;
    private String token;
    private int page=1;
    private int pageSize=10;

    private  int type;

    private Boolean refresh = true;

    List<OrderListResult.DataBean> data = new ArrayList<>();

    OrderListAdapter orderListAdapter;



    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
//        DaggerOrderComponent.builder()
//                .appComponent(appComponent)
//                .view(this)
//                .build()
//                .inject(this);
    }

    public static ProjectViewPagerFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(Constant.type, type);
        ProjectViewPagerFragment fragment = new ProjectViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
//        token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);
//        orderListAdapter = new OrderListAdapter(data);
//        rvOrder.setLayoutManager(new LinearLayoutManager(mContext));
//
//        rvOrder.addItemDecoration(new BaseDividerListItem(mContext,BaseDividerListItem.VERTICAL_LIST, ConvertUtils.dp2px(1),R.color.gray_f4));
//        rvOrder.setAdapter(orderListAdapter);
//        orderListAdapter.setOnCancelListener(new OrderListAdapter.OnClickCancelListener() {
//            @Override
//            public void cancelOrder(String orderNo) {  //取消订单
//                MyDialog.Builder mDialog = new MyDialog.Builder(mContext);
//                mDialog.setMessage("是否确认取消?");
//                mDialog.setNegativeButton(new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        mPresenter.cancelOrder(new CancelOrderPost(userId,token,orderNo));
//                    }
//                });
//                mDialog.create().show();
//            }
//        });
//        getData(page,pageSize);
//
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                refresh = true;
//                getData(1, 10);
//            }
//        });
//
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshlayout) {
//                page += 1;
//                refresh = false;
//                getData(page, 10);  //上拉加载添加数据
//            }
//        });

    }

    private void getData(int page, int pageSize) {
//        mPresenter.getOrderList(new OrderListPost(userId,token,page,pageSize,getArguments().getInt(Constant.type,0)));
    }


    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

//    @Override
//    public void getOrderListResult(OrderListResult orderListResult) {
//        showLoadSuccess();
//        if (refresh) {
//            data.clear();
//            if (orderListResult.getData().size() > 0) {
//                data.addAll(orderListResult.getData());
//                orderListAdapter.notifyDataSetChanged();
//            } else {
//                View emptyView=getLayoutInflater().inflate(R.layout.empty_view_order, null);
//                emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.MATCH_PARENT));
//                orderListAdapter.setEmptyView(emptyView);
//                orderListAdapter.notifyDataSetChanged();
//            }
//            refreshLayout.finishRefresh();
//            refreshLayout.setNoMoreData(false);
//        }else {
//            if (orderListResult.getData().size()  > 0) {
////                        history.clear();
//                //模拟网络请求到的数据
//                data.addAll(orderListResult.getData());
//                orderListAdapter.notifyDataSetChanged();
//                refreshLayout.finishLoadMore();
//            } else if (orderListAdapter.getData().size() < 10) {
//                Toast.makeText(mContext, "没有更多数据", Toast.LENGTH_SHORT).show();
//                refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
//            }
//        }
//    }


}
