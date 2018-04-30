package com.example.vveng.jianxiao.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ${vveng} on 2018/3/1 00:59.
 * 邮箱：vvengstuggle@163.com
 */

public abstract class BaseFragment extends Fragment {

    /**
     * 是否Fragment中的view加载完成
     */
    private boolean isViewCreated;

    /**
     * Fragment是否用户可见
     */
    private boolean isVisible;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyload();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    //isVisibleToUser表示UI是否可见
        if(isVisibleToUser){
            isVisible = true;
            lazyload();
        }else{
            isVisible = false;
        }
    }

    private void lazyload() {
        //必须确保onCreatedView加载完毕且界面可见时才加载数据
        if (isViewCreated && isVisible){
            loadData();
            //数据加载完毕重置为false，防止重复加载
            isViewCreated= false;
            isVisible = false;
        }
    }
    protected abstract void loadData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isVisible = false;
        isViewCreated = false;
    }
}
