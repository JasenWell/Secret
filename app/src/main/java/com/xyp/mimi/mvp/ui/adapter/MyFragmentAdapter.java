package com.xyp.mimi.mvp.ui.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;


public class MyFragmentAdapter extends FragmentStatePagerAdapter {
        String[]title={"全部","待支付","待审核","待发货","待收货","待评价"};
        List<Fragment> list;

        public void setList(List<Fragment> list) {
            this.list = list;
        }

        public MyFragmentAdapter(FragmentManager fm) {
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
