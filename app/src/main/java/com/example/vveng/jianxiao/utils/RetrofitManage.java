package com.example.vveng.jianxiao.utils;








import org.reactivestreams.Subscriber;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${vveng} on 2018/2/24 17:44.
 * 邮箱：vvengstuggle@163.com
 * 封装网络请求
 */

public class RetrofitManage {
    private static RetrofitManage retrofitManage;

    private Retrofit retrofit;
    private ApiStore apiSotre;
    public static RetrofitManage newInstance(){
        if(retrofitManage == null){
            retrofitManage = new RetrofitManage();
        }
        return retrofitManage;
    }

    public  ApiStore getApiStore(String url){
        if(apiSotre == null)
            build(url);
     return apiSotre;
    }

    private void build(String url) {
        retrofit  = new Retrofit.Builder()
                .client(getClient())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiSotre  = retrofit.create(ApiStore.class);
    }

    //添加拦截器，添加请求头部
    private OkHttpClient getClient(){
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = null;
                            request =chain.request()
                                    .newBuilder()
                                    .removeHeader("User--Agent")
                                   .addHeader("User--Agent","Music-vveng")
                                    .build();

                            return chain.proceed(request);
                        }

                    }).build();
            return okHttpClient;
    }


}
