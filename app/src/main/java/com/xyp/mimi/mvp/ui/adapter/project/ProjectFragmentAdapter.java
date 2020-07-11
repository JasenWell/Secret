package com.xyp.mimi.mvp.ui.adapter.project;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;


public class ProjectFragmentAdapter extends FragmentStatePagerAdapter {
        String[]title={};
        List<Fragment> list;

        public void setList(List<Fragment> list,String [] title) {
            this.list = list;
            this.title = title;
        }

        public ProjectFragmentAdapter(FragmentManager fm) {
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
