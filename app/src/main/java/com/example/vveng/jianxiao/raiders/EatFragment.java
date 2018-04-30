package com.example.vveng.jianxiao.raiders;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vveng.jianxiao.base.BaseFragment;
import com.example.vveng.jianxiao.R;

import com.example.vveng.jianxiao.raiders.model.EatItemBean;
import com.example.vveng.jianxiao.raiders.model.IEatShow;
import com.example.vveng.jianxiao.raiders.presenter.EatPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * 攻略页面的足不出户页面
 */

public class EatFragment extends BaseFragment implements IEatShow {


    @BindView(R.id.raiders_recyclerview)
    RecyclerView raidersRecyclerview;
    @BindView(R.id.raiders_srlayout)
    SmartRefreshLayout raidersSrlayout;
    Unbinder unbinder;

    private EatApdater apdater;
    private EatPresenter presenter;

    public static EatFragment newInstance(String s1) {
        EatFragment fragment = new EatFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", s1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_eat, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new EatPresenter(this);
        initRecyclerView();
        initSmartFresh();
        return view;
    }

    /**
     * 下拉上拉刷新
     */
    private void initSmartFresh() {

        raidersSrlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();

                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });


        raidersSrlayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Toast.makeText(getActivity(), "加载更多", Toast.LENGTH_SHORT).show();
                refreshlayout.finishLoadMore(2000);//传入false表示加载失败
            }
        });
    }


    /**
     * recyclerview
     */
    private void initRecyclerView() {
        presenter = new EatPresenter(this);

        LinearLayoutManager Manager = new LinearLayoutManager(getActivity());
        Manager.setOrientation(LinearLayoutManager.VERTICAL);
        raidersRecyclerview.setLayoutManager(Manager);

        //   Log.d("LoadEatData",datas.size()+"");
        apdater = new EatApdater(getActivity(), new ArrayList<EatItemBean>());
        raidersRecyclerview.setAdapter(apdater);

    }

    /**
     * 加载数据
     */
    @Override
    protected void loadData() {
        Toast.makeText(getActivity(), "eat加载了数据", Toast.LENGTH_SHORT).show();
        presenter.LoadEatData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 弹出Toas
     *
     * @param str
     */
    @Override
    public void ShowToast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoginSuccess(String str) {
        ShowToast(str);
    }

    @Override
    public void LoginError(String str) {
        ShowToast(str);
    }

    @Override
    public void LoadEatData(ArrayList<EatItemBean> datas) {
        Log.d("Eatfragment",datas.size()+"");
        apdater.setData(datas);
    }
}
