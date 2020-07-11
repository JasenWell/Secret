package com.xyp.mimi.mvp.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.ui.adapter.MyFragmentAdapter;
import com.xyp.mimi.mvp.ui.adapter.MyViewPager;
import com.xyp.mimi.mvp.ui.fragment.USDTBuyAndGuaFragment;
import com.xyp.mimi.mvp.ui.fragment.YueBuyAndGuaFragment;
import com.xyp.mimi.mvp.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChongzhiActivity extends BaseSupportActivity {

    @BindView(R.id.mViewPager)
    MyViewPager mViewPager;
    List<Fragment> list;
    MyFragmentAdapter adapter;
    @BindView(R.id.tl_my_order)
    TabLayout tl_all_order;
    @BindView(R.id.app_title)
    TextView appTitle;

    String type;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_chongzhi;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        type = getIntent().getStringExtra(AppConstant.Api.TYPE);
        appTitle.setText("会员");
        mViewPager.setScroll(false);
        tl_all_order.setSelectedTabIndicatorHeight(0);
        adapter = new MyFragmentAdapter(getSupportFragmentManager());

        list = new ArrayList<>();
        list.add( YueBuyAndGuaFragment.newInstance(type));
        list.add( USDTBuyAndGuaFragment.newInstance(type)); //入口
        adapter.setList(list);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(list.size()-1);
        tl_all_order.setupWithViewPager(mViewPager);
    }


}
