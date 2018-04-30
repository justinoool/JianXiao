package com.example.vveng.jianxiao.utils;

import com.example.vveng.jianxiao.home.model.HomeItemBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ${vveng} on 2018/2/24 14:54.
 * 邮箱：vvengstuggle@163.com
 * 地址类
 */

public interface ApiStore {
    //图片地址
    String photo_url = "http://gank.io/api/data/";

    @GET("福利/{num}/{ye}")
    Observable<HomeItemBean> getHomeItemPhotoData(@Path("num") int num,@Path("ye") int ye);

}
