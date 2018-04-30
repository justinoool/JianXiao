package com.example.vveng.jianxiao.raiders.model;

import java.util.ArrayList;

/**
 * Created by ${vveng} on 2018/3/22 09:16.
 * 邮箱：vvengstuggle@163.com
 */

public interface IEatShow {
    void ShowToast(String str);
    void LoginSuccess(String str);
    void LoginError(String str);
    void LoadEatData(ArrayList<EatItemBean> datas);
}
