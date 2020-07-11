package com.xyp.mimi.mvp.ui.activity.wallet;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.ui.adapter.MoneyFragmentAdapter;
import com.xyp.mimi.mvp.ui.adapter.MyViewPager;
import com.xyp.mimi.mvp.ui.fragment.NullFragment;
import com.xyp.mimi.mvp.ui.fragment.wallet.AllFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
public class MoneyDetailActivity extends BaseSupportActivity {
    @BindView(R.id.app_title)
    TextView appTitle;

    @BindView(R.id.tl_my_order)
    TabLayout tlMyOrder;
    List<Fragment> list;
    MoneyFragmentAdapter adapter;
    @BindView(R.id.mViewPager)
    MyViewPager mViewPager;

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .init();
    }


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_moneydetail;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        appTitle.setText("交易明细");
//        tlMyOrder.setSelectedTabIndicatorHeight(0);
        adapter = new MoneyFragmentAdapter(getSupportFragmentManager());
//
        list = new ArrayList<>();
        list.add(new AllFragment());
        list.add(new NullFragment());
        adapter.setList(list);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(list.size()-1);
        tlMyOrder.setupWithViewPager(mViewPager);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
