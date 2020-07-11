package com.xyp.mimi.mvp.ui.adapter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MoneyFragmentAdapter extends FragmentStatePagerAdapter {
    String[]title={"全部","充值记录"};
    List<Fragment> list;

    public void setList(List<Fragment> list) {
        this.list = list;
    }

    public MoneyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
//        return super.getPageTitle(position);
    }
}