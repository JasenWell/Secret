package com.xyp.mimi.mvp.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.SPUtils;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.contract.TaskWebContract;
import com.xyp.mimi.mvp.http.entity.TaskData;
import com.xyp.mimi.mvp.http.entity.product.Product;
import com.xyp.mimi.mvp.presenter.TaskWebPresenter;
import com.xyp.mimi.mvp.ui.adapter.MyPagerAdapter;
import com.xyp.mimi.mvp.ui.fragment.AllWebFragment;
import com.xyp.mimi.mvp.ui.fragment.NullFragment;
import com.xyp.mimi.mvp.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class WebFragmentActivity extends BaseSupportActivity<TaskWebPresenter> implements TaskWebContract.View {


    String url;
    @BindView(R.id.mTb)
    TabLayout mTb;
    @BindView(R.id.mVp)
    ViewPager mVp;

    @BindView(R.id.app_title)
    TextView appTitle;
    private List<Fragment> mFragmentList;
    private List<String> mTitleList;
    private int tabSize = 0;
    private AllWebFragment allWebFragment;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
//        DaggerTaskWebComponent.builder()
//                .appComponent(appComponent)
//                .taskWebModule(new TaskWebModule(this))
//                .build()
//                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_web_fragment;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        appTitle.setText("掌刷宝");
        url = getIntent().getStringExtra("URL");
       ;//
        //把传过来的url传到每个fragment

        //获取tab个数
        mPresenter.getRecommend( SPUtils.getInstance().getString(AppConstant.User.USER_ID));
//        getdata();
//        initFragment();

    }

    private void initFragment() {
        getSupportFragmentManager()    //
                .beginTransaction()
                .add(R.id.fragment_container,new AllWebFragment())   // 此处的R.id.fragment_container是要盛放fragment的父容器
                .commit();

//        allWebFragment = AllWebFragment.newInstance( SPUtils.getInstance().getString(AppConstant.User.USEID),0 + 1 + "");
        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();
        if (tabSize > 0) {
            //设置tablayout模式
            mTb.setTabMode(TabLayout.MODE_SCROLLABLE);
            for (int i = 0; i < tabSize; i++) {
                mTitleList.add(i + 1 + "号");
                mTb.addTab(mTb.newTab().setText(i + 1 + ""));
                mFragmentList.add(new NullFragment());
                Log.d("webfragment",i+"");
            }
            mVp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragmentList, mTitleList));
            //将tablayout与fragment关联
            mTb.setupWithViewPager(mVp);
            //Viewpager的监听（这个接听是为Tablayout专门设计的）
            mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTb));
//TabLayout的监听
            mTb.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int position = tab.getPosition();

                    mVp.setCurrentItem(position);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        }
    }

    @Override
    public void getTaskSuccess(TaskData taskData) {

    }

    @Override
    public void getRecommendList(Product data) {
        //这个方法是大于0的时候才会调用，
        tabSize = data.getCon().size();
        initFragment();
    }

    @Override
    public void showMessage(@NonNull String message) {
        //没有的时候调用
        tabSize = 0;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
