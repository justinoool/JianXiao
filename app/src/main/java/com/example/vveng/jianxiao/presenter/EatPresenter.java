package com.example.vveng.jianxiao.presenter;

import android.content.Context;

import com.example.vveng.jianxiao.model.EatItemBean;
import com.example.vveng.jianxiao.model.EatModelImp;
import com.example.vveng.jianxiao.model.IEatModel;
import com.example.vveng.jianxiao.model.IEatShow;

import java.util.ArrayList;

/**
 * Created by ${vveng} on 2018/3/5 21:55.
 * 邮箱：vvengstuggle@163.com
 * 攻略页面的足不出户 实现类
 */

public class EatPresenter implements IEatPresenter{
    private IEatModel iEatModel;
    private IEatShow iEatShow;

    public EatPresenter(IEatShow iEatShow) {
        this.iEatShow = iEatShow;
        this.iEatModel = new EatModelImp(iEatShow);
    }

    @Override
    public void LoadEatData() {
        if (iEatShow!=null){
            iEatShow.ShowToast("登陆中...");   //实现了P层可以直接操作view层
        }
          iEatModel.LoadEatData();
    }
}
