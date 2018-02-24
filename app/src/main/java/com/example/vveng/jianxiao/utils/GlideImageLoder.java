package com.example.vveng.jianxiao.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.vveng.jianxiao.R;
import com.lzy.ninegrid.NineGridView;

/**
 * Created by ${vveng} on 2018/2/24 12:55.
 * 邮箱：vvengstuggle@163.com
 */

public class GlideImageLoder implements NineGridView.ImageLoader {
    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_home_preloading)
                .error(R.drawable.ic_home_preloading)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                ;

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }
}
