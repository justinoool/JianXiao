package com.example.vveng.jianxiao.home.presenter;

import android.content.Context;
import android.util.Log;

import com.example.vveng.jianxiao.R;
import com.example.vveng.jianxiao.home.model.HomeItemBean;
import com.example.vveng.jianxiao.utils.ApiStore;
import com.example.vveng.jianxiao.utils.RetrofitManage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${vveng} on 2018/2/20 15:39.
 * 邮箱：vvengstuggle@163.com
 */

public class HomePresenter {
    private final  static  String TAG = "HomePresenter";
    private Context context;
    private IHomeFragment iHomeFragment;
    //private ArrayList<HomeItemBean> arrayList = new ArrayList<>();
    private String[] username;
    private String[] strings;
    private int[] tx;

    public HomePresenter(Context context, IHomeFragment iHomeFragment) {
        this.context = context;
        this.iHomeFragment = iHomeFragment;
    }

    public void loaddata() {
        username = new String[]{
                "谢春花", "陈奕迅", "Jam", "Charlie Puth", "陈粒"
        };
        tx = new int[]{R.mipmap.boy, R.mipmap.grid};
        strings = new String[]{
                "2017年8月21日，在广州南站三层候车大厅错过了人生中最有感觉的一个女孩，看了她好久，中间有一两次短暂的对视，却不敢上前跟她打招呼，动车D3858,13:44开，检票的时候她还在前面，看着她上了4号车厢,但在车上我没有行动，直到我出站的时候，忽然之间好像失去了全世界",

                "只有钢琴的伴奏，这样的唱，所有的失误和瑕疵藏都藏不住，没有和声和其他的乐器铺助，敢这样唱的一般都很有种，声音的掌控力不是一般的好，所长先生的情感处理和声音控制，在华语男歌手里面肯定是前三。",

                "在读大学，她说来我的城市来找我，我说你来了我包养你，那三天我花光了自己赚了2年的钱，其实我就是想告诉她，现在我能养你，以后也能。",

                "你不愿回家，去玩吧。你很忙，去忙吧。你不让人们知道我，那我就当自己不存在吧。你没到家不方便说话，那我就闭嘴吧。我只能克制自己，无条件。",

                "喜欢一个人，就是看着她笑自己也会笑，再聪明再勇敢的我在她面前会变得笨拙，当她受伤时不知所措的心疼，当疲倦的夜晚只要想起她也有了坚持下去的动力和信心。这一切很简单，只是因为我喜欢她。",
        };
        Random random = new Random();
        int i = random.nextInt(8)+1;
        int j = random.nextInt(19)+1;
        Log.d(TAG,"i:"+i+"j:"+j);
        Observable<HomeItemBean> observable = RetrofitManage.newInstance()
                .getApiStore(ApiStore.photo_url)
                .getHomeItemPhotoData(1, 1);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeItemBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeItemBean Bean) {
                        ArrayList<HomeItemBean> arrayList = new ArrayList<>();
                        List<HomeItemBean.ResultsBean> beans = Bean.getResults();
                            for(int i=0;i<5;i++){
                            HomeItemBean homeItemBean  = new HomeItemBean();
                            homeItemBean.setUsername(username[i]);
                            homeItemBean.setContext(strings[i]);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String data = sdf.format(new Date());
                            homeItemBean.setTime(data);
                            homeItemBean.setUsername_tx(i%2==0? tx[0]:tx[1] );
                            homeItemBean.setResults(beans);
                            arrayList.add(homeItemBean);
                        }
                        iHomeFragment.loaddata(arrayList);
                        Log.d(TAG,"onNext:"+Bean.getResults().size());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
