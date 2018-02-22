package com.example.vveng.jianxiao.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vveng.jianxiao.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {


    public static ChatFragment newInstance(String s1) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", s1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

}
