package com.marcus.wechatnews.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcus.wechatnews.R;
import com.marcus.wechatnews.model.NewsModel;
import com.marcus.wechatnews.ui.adapter.NewsListAdapter.MyViewHolder;
import com.marcus.wechatnews.ui.webview.WebViewActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marcus on 16/8/12.
 */
public class NewsListAdapter extends RecyclerView.Adapter<MyViewHolder> {

    //没题图时默认显示,否则 picasso 将报错
    private final static String DEFAULT = "http://zxpic.gtimg.com/infonew/0/wechat_pics_-7132408.jpg/640";

    private Context context;
    private List<NewsModel.ResultBean.ListBean> newsList;

    @Inject
    public NewsListAdapter() {
    }

    public void setData(List<NewsModel.ResultBean.ListBean> newsList) {
        this.newsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_newslist, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //标题
        holder.title.setText(newsList.get(position).getTitle());
        //题图
        Picasso.with(context)
                .load(newsList.get(position).getFirstImg().equals("") ? DEFAULT : newsList.get(position).getFirstImg())
                .into(holder.image);
        //点击事件
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> result = new ArrayList<>();
                result.add(0, newsList.get(holder.getAdapterPosition()).getUrl());
                result.add(1, newsList.get(holder.getAdapterPosition()).getTitle());
                result.add(2, newsList.get(holder.getAdapterPosition()).getSource());
                result.add(3, newsList.get(holder.getAdapterPosition()).getFirstImg());
                WebViewActivity.startActivity(context, result);
                ((Activity) context).overridePendingTransition(R.anim.activity_in_anim, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList != null ? newsList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_title)
        TextView title;
        @BindView(R.id.news_image)
        ImageView image;
        @BindView(R.id.news_card)
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
