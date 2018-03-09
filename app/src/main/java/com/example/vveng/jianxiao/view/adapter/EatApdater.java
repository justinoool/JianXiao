package com.example.vveng.jianxiao.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.vveng.jianxiao.R;
import com.example.vveng.jianxiao.model.EatItemBean;
import com.lid.lib.LabelTextView;
import com.lid.lib.LabelView;

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

    public void setData(ArrayList<EatItemBean> datas) {
        if (datas.size() != 0) {
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
        holder.end.setText(beans.get(position).getEnd());
        holder.money.setText(beans.get(position).getCost());
        int type = beans.get(position).getType();

        if (type == 1) {

            holder.labelTextView.setLabelText("快递");

        } else if (type == 2) {


            holder.labelTextView.setLabelText("食物");

        } else if (type == 3) {


            holder.labelTextView.setLabelText("代课");

        }


    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class EatViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rl;
        private TextView start, end, money, username, time;
        private CircleImageView circleImageView;
        private CardView cardView;
        private LabelTextView labelTextView;
        public EatViewHolder(View itemView) {
            super(itemView);
            rl = itemView.findViewById(R.id.raiders_contant_detail_layout);
            circleImageView = itemView.findViewById(R.id.raiders_contant_detail_tx);
            username = itemView.findViewById(R.id.raiders_contant_detail_usename);
            time = itemView.findViewById(R.id.raiders_contant_detail_time);
            start = itemView.findViewById(R.id.raiders_eat_start);
            end = itemView.findViewById(R.id.raiders_eat_end);
            money = itemView.findViewById(R.id.raiders_eat_money);
            cardView = itemView.findViewById(R.id.raiders_cardview);
           labelTextView = itemView.findViewById(R.id.raiders_labeltext);
        }
    }
}
