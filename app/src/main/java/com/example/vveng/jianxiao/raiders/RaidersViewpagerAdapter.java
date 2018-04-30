package com.example.vveng.jianxiao.raiders;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ${vveng} on 2018/3/1 00:58.
 * 邮箱：vvengstuggle@163.com
 */

public class RaidersViewpagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private ArrayList<Fragment> fragments;

    public RaidersViewpagerAdapter(FragmentManager fm,String[] datas,ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.titles = datas;
        this.fragments = fragmentArrayList;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles==null? null : titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments==null? null : fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.length==0? 0 : titles.length;
    }
}
