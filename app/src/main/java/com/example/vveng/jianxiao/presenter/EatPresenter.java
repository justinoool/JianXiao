package com.example.vveng.jianxiao.presenter;

import android.content.Context;

import com.example.vveng.jianxiao.model.EatItemBean;
import com.example.vveng.jianxiao.presenter.home.IEatFragment;

import java.util.ArrayList;

/**
 * Created by ${vveng} on 2018/3/5 21:55.
 * 邮箱：vvengstuggle@163.com
 */

public class EatPresenter {
    private IEatFragment iEatFragment;
    private ArrayList<EatItemBean> beans;
    private Context context;

    public EatPresenter(IEatFragment iEatFragment, Context context) {
        this.iEatFragment = iEatFragment;
        this.context = context;
    }

    public void loadEatData(){
        String[] start={"前门菜鸟驿站快递","商业街银辉肠粉","崇礼楼菜鸟驿站快递","一饭二楼打包"};
        String[] end={"思诚A栋405宿舍","砺能704宿舍","明德楼505宿舍","博雅报告厅"};
        String[] cost={"5元","3元","10元","4元"};
        int[] type = {1,2,3,1};
        beans = new ArrayList<>();
        for (int i =0;i<start.length;i++){
            EatItemBean bean = new EatItemBean();
            bean.setCost(cost[i]);
            bean.setStart(start[i]);
            bean.setEnd(end[i]);
            bean.setType(type[i]);
            beans.add(bean);
        }
        iEatFragment.LoadEatData(beans);
    }

}
