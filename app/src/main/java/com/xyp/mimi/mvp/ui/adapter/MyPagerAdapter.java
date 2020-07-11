package com.xyp.mimi.mvp.ui.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
    //添加fragment的集合
    private List<Fragment> mFragmentList;
    //添加标题的集合
    private List<String> mTilteLis;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> tilteLis) {
        super(fm);
        mFragmentList = fragmentList;
        mTilteLis = tilteLis;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

//    //获取标题
    @Override
    public CharSequence getPageTitle(int position) {

        return mTilteLis.get(position);
    }
}
