package com.example.vveng.jianxiao.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.vveng.jianxiao.R;
import com.example.vveng.jianxiao.model.EatItemBean;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ${vveng} on 2018/3/5 21:52.
 * 邮箱：vvengstuggle@163.com
 */

public class EatApdater extends RecyclerView.Adapter<EatApdater.EatViewHolder> {
    private Context context;
    private ArrayList<EatItemBean> beans = new ArrayList<>();

    public EatApdater(Context context, ArrayList<EatItemBean> beans) {
        this.context = context;
        this.beans = beans;
        notifyDataSetChanged();
    }

    public void setData(ArrayList<EatItemBean> datas){
        if(datas.size() != 0){
            datas.clear();
        }
        this.beans = datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.raiders_eat_item, parent, false);
        EatViewHolder holder = new EatViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EatViewHolder holder, int position) {
            holder.start.setText(beans.get(position).getStart());

    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class EatViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rl;
        private TextView start,username,time;
        private CircleImageView circleImageView;
        public EatViewHolder(View itemView) {
            super(itemView);
            rl=itemView.findViewById(R.id.home_contant_detail);
            circleImageView = rl.findViewById(R.id.home_contant_detail_tx);
            username = rl.findViewById(R.id.home_contant_detail_usename);
            time = rl.findViewById(R.id.home_contant_detail_time);
            start =itemView.findViewById(R.id.raiders_eat_start);
        }
    }
}
