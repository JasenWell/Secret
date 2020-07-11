package com.xyp.mimi.mvp.ui.activity.wallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailPost;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailResult;
import com.xyp.mimi.mvp.presenter.wallet.MyEarningAndTixianPresenter;
import com.xyp.mimi.mvp.ui.adapter.wallet.MoneyDetailAdapter;
import com.xyp.mimi.mvp.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EarningAndTixianActivity extends BaseSupportActivity<MyEarningAndTixianPresenter> implements BankContract.MyEarningAndTixianView  {

    @BindView(R.id.app_title)
    TextView appTitle;

    @BindView(R.id.rv_jilu)
    RecyclerView rvJilu;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    MoneyDetailAdapter moneyDetailAdapter;

    int page=1;
    int pageSize=10;
    private Boolean refresh=true;
    List<MoneyDetailResult.DataBean.ListBean> list = new ArrayList<>();

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
//        DaggerBankComponent.builder()
//                .appComponent(appComponent)
//                .bankModule(new BankModule(this))
//                .build()
//                .injectEarnAndTixian(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_earning_tixian_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        String  type = getIntent().getStringExtra("TYPE");
        if(type.equals("shouyimingxi")){
            appTitle.setText("收益明细");
        }else{
            appTitle.setText("提现记录");
        }
        MoneyDetailPost moneyDetailPost = new MoneyDetailPost();
        moneyDetailPost.setUserId(SPUtils.getInstance().getString(AppConstant.User.USER_ID));
        moneyDetailPost.setToken(SPUtils.getInstance().getString(AppConstant.User.TOKEN));
        moneyDetailPost.setPage(page);
        moneyDetailPost.setPageSize(pageSize);
        moneyDetailPost.setDate("2020-05");
        mPresenter.getMyEarningDetail(moneyDetailPost);
        moneyDetailAdapter = new MoneyDetailAdapter(R.layout.item_jiaoyimingxi, list,1);
        LinearLayoutManager xLinearLayoutManager = new LinearLayoutManager(mContext);
        rvJilu.setAdapter(moneyDetailAdapter);
        rvJilu.setLayoutManager(xLinearLayoutManager);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                hideLoading();
                refresh = true;
                getData(1,10,"2020-05");
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page += 1;
                refresh = false;
                getData(page,10,"2020-05");  //上拉加载添加数据
            }
        });
    }

    private void getData( int page, int pageSize,String date) {
        MoneyDetailPost moneyDetailPost = new MoneyDetailPost();
        moneyDetailPost.setUserId(SPUtils.getInstance().getString(AppConstant.User.USER_ID));
        moneyDetailPost.setToken(SPUtils.getInstance().getString(AppConstant.User.TOKEN));
        moneyDetailPost.setPage(page);
        moneyDetailPost.setPageSize(pageSize);
        moneyDetailPost.setDate(date);
        mPresenter.getMyEarningDetail(moneyDetailPost);

    }


    @Override
    public void getMyEarningResult(BaseResponse<Object> myEarningResult) {
        showLoadSuccess();


        if (refresh) {
            moneyDetailAdapter.removeAllHeaderView();
            View headerView =  LayoutInflater.from(mContext).inflate(R.layout.item_jiaoyimingxi_header, null);
            TextView date = headerView.findViewById(R.id.tv_date);
            TextView totalMoney = headerView.findViewById(R.id.iv_total_money);
//          date.setText();
            totalMoney.setText("");
            moneyDetailAdapter.addHeaderView(headerView);

            list.clear();
//            if (myEarningResult.getData().getList().size() > 0) {
//                list.addAll(myEarningResult.getData().getList());
//                moneyDetailAdapter.notifyDataSetChanged();
//            } else {
//                moneyDetailAdapter.notifyDataSetChanged();
//            }
            refreshLayout.finishRefresh();
            refreshLayout.setNoMoreData(false);
        }
        else {
//            if (moneyDetailResult.getData().getList().size()  > 0) {
////                        history.clear();
//                //模拟网络请求到的数据
//                list.addAll(moneyDetailResult.getData().getList());
//                moneyDetailAdapter.notifyDataSetChanged();
//                refreshLayout.finishLoadMore();
//            } else if (moneyDetailResult.getData().getList().size() < 10) {
//                Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
//                refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
//            }
        }
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
