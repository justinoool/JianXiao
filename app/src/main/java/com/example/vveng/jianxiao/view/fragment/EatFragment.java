package com.example.vveng.jianxiao.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vveng.jianxiao.R;

import com.example.vveng.jianxiao.model.EatItemBean;
import com.example.vveng.jianxiao.presenter.EatPresenter;
import com.example.vveng.jianxiao.presenter.home.IEatFragment;
import com.example.vveng.jianxiao.view.adapter.EatApdater;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */

public class EatFragment extends BaseFragment implements IEatFragment {


    @BindView(R.id.raiders_recyclerview)
    RecyclerView raidersRecyclerview;
    @BindView(R.id.raiders_srlayout)
    SmartRefreshLayout raidersSrlayout;
    Unbinder unbinder;

    private EatApdater apdater;
    private EatPresenter presenter;
    private ArrayList<EatItemBean> arrayList = new ArrayList<>();


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
        initRecyclerView();

        return view;
    }


    /**
     * recyclerview
     */
    private void initRecyclerView() {
        LinearLayoutManager Manager = new LinearLayoutManager(getActivity());
        Manager.setOrientation(LinearLayoutManager.VERTICAL);
        raidersRecyclerview.setLayoutManager(Manager);
         apdater = new EatApdater(getActivity(),arrayList);
         raidersRecyclerview.setAdapter(apdater);

    }

    @Override
    protected void loadData() {
        Toast.makeText(getActivity(), "eat加载了数据", Toast.LENGTH_SHORT).show();

        presenter = new EatPresenter(this,getActivity());
        presenter.loadEatData();
    }

    @Override
    public void LoadEatData(ArrayList<EatItemBean> datas) {
        apdater.setData(datas);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }
}
