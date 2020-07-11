package com.xyp.mimi.mvp.ui.activity.order;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.http.Constant;
import com.xyp.mimi.mvp.ui.adapter.MyFragmentAdapter;
import com.xyp.mimi.mvp.ui.adapter.MyViewPager;
import com.xyp.mimi.mvp.ui.fragment.AllOrderFragment;
import com.xyp.mimi.mvp.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class OrderListActivity extends BaseSupportActivity {

    @BindView(R.id.app_title)
    TextView appTitle;

    @BindView(R.id.tl_my_order)
    TabLayout tlMyOrder;

    @BindView(R.id.mViewPager)
    MyViewPager mViewPager;

    MyFragmentAdapter adapter;

    List<Fragment> list;

    AllOrderFragment allOrderFragment;
    AllOrderFragment daiFuKuanOrderFragment;
    AllOrderFragment daiShenHeOrderFragment;
    AllOrderFragment daiFaHuoOrderFragment;
    AllOrderFragment daiShouHuoOrderFragment;
    AllOrderFragment daiPingJiaOrderFragment;
    @BindView(R.id.v_bottom_line)
    View vBottomLine;

    int index;
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

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_order;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        setAppTitle("我的订单");
        index =  getIntent().getIntExtra(AppConstant.User.ORDER_INDEX, 0);
        vBottomLine.setVisibility(View.GONE);
        mViewPager.setScroll(false);
        adapter = new MyFragmentAdapter(getSupportFragmentManager());

        list = new ArrayList<>();
        list.add(allOrderFragment.newInstance(Constant.type_0));
        list.add(daiFuKuanOrderFragment.newInstance(Constant.type_1));//待支付
        list.add(daiShenHeOrderFragment.newInstance(Constant.type_6));//待审核
        list.add(daiFaHuoOrderFragment.newInstance(Constant.type_2));//待发货
        list.add(daiShouHuoOrderFragment.newInstance(Constant.type_3));//待收货
        list.add(daiPingJiaOrderFragment.newInstance(Constant.type_4));//待评价
        adapter.setList(list);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(list.size() - 1);
        tlMyOrder.setupWithViewPager(mViewPager);
        tlMyOrder.getTabAt(index).select(); //默认选中某项放在加载viewpager之后

    }


}
