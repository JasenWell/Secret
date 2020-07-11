package com.xyp.mimi.mvp.ui.fragment.wallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportFragment;
import com.xyp.mimi.mvp.contract.wallet.MoneyDetailContract;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailPost;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailResult;
import com.xyp.mimi.mvp.presenter.wallet.MoneyDetailPresenter;
import com.xyp.mimi.mvp.ui.adapter.wallet.MoneyDetailAdapter;
import com.xyp.mimi.mvp.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class AllFragment extends BaseSupportFragment<MoneyDetailPresenter> implements MoneyDetailContract.View {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
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
                .init();
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
//        DaggerWalletComponent.builder()
//                .appComponent(appComponent)
//                .walletModule(new WalletModule(this))
//                .build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wallet_all, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        MoneyDetailPost moneyDetailPost = new MoneyDetailPost();
        moneyDetailPost.setUserId(SPUtils.getInstance().getString(AppConstant.User.USER_ID));
        moneyDetailPost.setToken(SPUtils.getInstance().getString(AppConstant.User.TOKEN));
        moneyDetailPost.setPage(page);
        moneyDetailPost.setPageSize(pageSize);
        moneyDetailPost.setType(0);//全部
        moneyDetailPost.setDate("2020-05");
        mPresenter.getMoneyDetail(moneyDetailPost);
        moneyDetailAdapter = new MoneyDetailAdapter(R.layout.item_jiaoyimingxi, list,1);
        LinearLayoutManager xLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setAdapter(moneyDetailAdapter);
        recyclerview.setLayoutManager(xLinearLayoutManager);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
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
        moneyDetailPost.setType(0);//全部
        moneyDetailPost.setDate(date);
        mPresenter.getMoneyDetail(moneyDetailPost);

    }
    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void getMoneyDetail(MoneyDetailResult moneyDetailResult) {
        showLoadSuccess();
        if (refresh) {
            moneyDetailAdapter.removeAllHeaderView();
            View  headerView =  LayoutInflater.from(getActivity()).inflate(R.layout.item_jiaoyimingxi_header, null);
            TextView date = headerView.findViewById(R.id.tv_date);
            TextView totalMoney = headerView.findViewById(R.id.iv_total_money);
//          date.setText();
            totalMoney.setText(moneyDetailResult.getData().getAmountMon_hz());
            moneyDetailAdapter.addHeaderView(headerView);

            list.clear();
            if (moneyDetailResult.getData().getList().size() > 0) {
                list.addAll(moneyDetailResult.getData().getList());
                moneyDetailAdapter.notifyDataSetChanged();
            } else {
                moneyDetailAdapter.notifyDataSetChanged();
            }
            refreshLayout.finishRefresh();
            refreshLayout.setNoMoreData(false);
        }else {
            if (moneyDetailResult.getData().getList().size()  > 0) {
//                        history.clear();
                //模拟网络请求到的数据
                list.addAll(moneyDetailResult.getData().getList());
                moneyDetailAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            } else if (moneyDetailResult.getData().getList().size() < 10) {
                Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
            }
        }
    }
}
