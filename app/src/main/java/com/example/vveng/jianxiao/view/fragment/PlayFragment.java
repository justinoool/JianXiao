package com.example.vveng.jianxiao.view.fragment;


import android.content.Context;
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
public class PlayFragment extends BaseFragment{



    public static PlayFragment newInstance(String s1) {
        PlayFragment fragment = new PlayFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", s1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play, container, false);
    }

    @Override
    protected void loadData() {
        Toast.makeText(getActivity(), "play加载了数据", Toast.LENGTH_SHORT).show();
    }
}
