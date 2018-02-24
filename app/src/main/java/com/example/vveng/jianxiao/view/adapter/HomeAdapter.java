package com.example.vveng.jianxiao.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vveng.jianxiao.R;
import com.example.vveng.jianxiao.model.HomeItemBean;
import com.example.vveng.jianxiao.utils.GlideImageLoder;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ${vveng} on 2018/2/20 13:54.
 * 邮箱：vvengstuggle@163.com
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewholder> {

    private Context context;
    private ArrayList<HomeItemBean> datas = new ArrayList<>();
    private CircleImageView circleImageView;
    private TextView username, time;
    private ImageView off;

    public HomeAdapter(Context context, ArrayList<HomeItemBean> datas) {
        this.context = context;
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setData(ArrayList<HomeItemBean> data) {
        datas = data;

        notifyDataSetChanged();
    }

    @Override
    public HomeViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_ryview_item, parent, false);
        View v = LayoutInflater.from(context).inflate(R.layout.home_contant_detail,parent,false);
          circleImageView = v.findViewById(R.id.home_contant_detail_tx);
         username = v.findViewById(R.id.home_contant_detail_usename);
          time = v.findViewById(R.id.home_contant_detail_time);
         off = v.findViewById(R.id.home_contant_detail_off);


        HomeViewholder holder = new HomeViewholder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(HomeViewholder holder, int position) {
        holder.textView.setOrientation(LinearLayout.VERTICAL);
        holder.textView.setText("       " + datas.get(position));
        holder.textView.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                Toast.makeText(context, isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });
        //  holder.circleImageView.setImageResource(datas.get(position).getUsername_tx());
        //  holder.username.setText(datas.get(position).getUsername());
        //   holder.time.setText(datas.get(position).getTime());
        circleImageView .setImageResource(datas.get(position).getUsername_tx());
        username.setText(datas.get(position).getUsername());
        time.setText(datas.get(position).getTime());
        holder.nineGridView.setMaxSize(9);

        ArrayList<ImageInfo> imageInfos = new ArrayList<>();
        List<HomeItemBean.ResultsBean> results = datas.get(position).getResults();
        if (results != null) {
            for (HomeItemBean.ResultsBean data : results) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setBigImageUrl(data.getUrl());
                imageInfo.setThumbnailUrl(data.getUrl());
                imageInfos.add(imageInfo);
            }
        }
        holder.nineGridView.setAdapter(new NineGridViewClickAdapter(context, imageInfos));

        if (imageInfos != null && imageInfos.size() == 1) {
            holder.nineGridView.setSingleImageRatio(1.0f);
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public class HomeViewholder extends RecyclerView.ViewHolder {
        private ExpandableTextView textView;
        private RelativeLayout rl;
        private NineGridView nineGridView;
        private CircleImageView circleImageView;
        private TextView username, time;
        private ImageView off;

        @SuppressLint("WrongViewCast")
        public HomeViewholder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.home_expand_text_view);
            nineGridView = itemView.findViewById(R.id.home_item_nine);
            //  circleImageView = rl.findViewById(R.id.home_contant_detail_tx);
            // username = rl.findViewById(R.id.home_contant_detail_usename);
            //  time = rl.findViewById(R.id.home_contant_detail_time);
            // off = rl.findViewById(R.id.home_contant_detail_off);
        }
    }
}
