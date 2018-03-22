package com.example.vveng.jianxiao.model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ${vveng} on 2018/3/22 09:20.
 * 邮箱：vvengstuggle@163.com
 * 类： EatFragment的Model实现类，负责网络请求 业务逻辑
 */

public class EatModelImp implements IEatModel {

    private IEatShow iEatShow;
    private ArrayList<EatItemBean> beans;

    public EatModelImp(IEatShow iEatShow) {
        this.iEatShow = iEatShow;
    }

    @Override
    public void LoadEatData() {
        String[] start = {"前门菜鸟驿站快递", "商业街银辉肠粉", "崇礼楼菜鸟驿站快递", "一饭二楼打包"};
        String[] end = {"思诚A栋405宿舍", "砺能704宿舍", "明德楼505宿舍", "博雅报告厅"};
        String[] cost = {"5元", "3元", "10元", "4元"};
        int[] type = {1, 2, 3, 1};

        beans = new ArrayList<>();
        for (int i = 0; i < start.length; i++) {
            EatItemBean bean = new EatItemBean();
            bean.setCost(cost[i]);
            bean.setStart(start[i]);
            bean.setEnd(end[i]);
            bean.setType(type[i]);
            beans.add(bean);
        }
        Log.d("EatModelImp",beans.size()+"");
        iEatShow.LoadEatData(beans);
        iEatShow.ShowToast("加载完成！");
    }
}
