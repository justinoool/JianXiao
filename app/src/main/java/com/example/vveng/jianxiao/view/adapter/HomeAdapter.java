package com.example.vveng.jianxiao.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vveng.jianxiao.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

/**
 * Created by ${vveng} on 2018/2/20 13:54.
 * 邮箱：vvengstuggle@163.com
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewholder> {
    private Context context;
    private ArrayList<String> datas = new ArrayList<>();

    public HomeAdapter(Context context, ArrayList<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public HomeAdapter.HomeViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_ryview_item, parent, false);
        HomeViewholder holder = new HomeViewholder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(HomeAdapter.HomeViewholder holder, int position) {
         holder.textView.setOrientation(LinearLayout.VERTICAL);
         holder.textView.setText("    "+datas.get(position));
        holder.textView.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                Toast.makeText(context, isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class HomeViewholder extends RecyclerView.ViewHolder {
        private ExpandableTextView textView;
        private CardView cardView;
        public HomeViewholder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.home_expand_text_view);
        //    cardView = itemView.findViewById(R.id.home_cdlayout);
        }
    }
}
