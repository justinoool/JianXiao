package com.example.vveng.jianxiao.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vveng.jianxiao.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EatFragment extends BaseFragment {


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
        return inflater.inflate(R.layout.fragment_eat, container, false);
    }

    @Override
    protected void loadData() {
        Toast.makeText(getActivity(), "eat加载了数据", Toast.LENGTH_SHORT).show();
    }
}
