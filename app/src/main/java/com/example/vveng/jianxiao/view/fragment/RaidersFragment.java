package com.example.vveng.jianxiao.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.vveng.jianxiao.R;


import com.example.vveng.jianxiao.view.adapter.home.RaidersViewpagerAdapter;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class RaidersFragment extends Fragment {


    @BindView(R.id.raiders_layout_title)
    RelativeLayout raidersLayoutTitle;
    @BindView(R.id.raiders_tablayout)
    TabLayout raidersTablayout;
    @BindView(R.id.raiders_viewpager)
    ViewPager raidersViewpager;
    Unbinder unbinder;

    private ArrayList<Fragment> fragments;
    private String[] titles ;
    private RaidersViewpagerAdapter viewpageradapter;
    public static RaidersFragment newInstance(String s1) {
        RaidersFragment fragment = new RaidersFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", s1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_raiders, container, false);
        unbinder = ButterKnife.bind(this, view);

        initViewpager();
        return view;
    }

    private void initViewpager() {
        titles = getResources().getStringArray(R.array.raiders_tablayout);
        fragments = new ArrayList<>();
        fragments.add(EatFragment.newInstance("eat"));
        fragments.add(PlayFragment.newInstance("play"));
        fragments.add(ShoppingFragment.newInstance("shop"));
        viewpageradapter = new RaidersViewpagerAdapter(getChildFragmentManager(),titles,fragments);
        raidersViewpager.setAdapter(viewpageradapter);
        //去除两侧光晕
        raidersViewpager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        raidersTablayout.setTabMode(TabLayout.MODE_FIXED);
        raidersTablayout.setupWithViewPager(raidersViewpager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
