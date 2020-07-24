package com.btechbuddy.newsindiaexample;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

//First step is to create ViewHolder and Then create its constructor
//Secon step is to add the custom adapter and then impliment the Methods Like Oncreate,OnBind Etc...
public class AdapterNews extends RecyclerView.Adapter<AdapterNews.NewsVH>{

    Context context;
    ArrayList<ModelOfNews> Data;
// Second Step
    public AdapterNews(Context context, ArrayList<ModelOfNews> data) {
        this.context = context;
        Data = data;
    }

    @NonNull
    @Override
    public NewsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        This step gives View to the RecyclerView.ViewHolder and Then Returns the VieewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_news_item,parent,false);
        NewsVH NewsVH = new NewsVH(view);
        return NewsVH;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsVH holder, int position) {
        final ModelOfNews dataItem = Data.get(position);

        holder.txtTitle.setText(dataItem.newsTitle);
        holder.txtDesc.setText(dataItem.newsDesc);
        Glide.with(context).load(dataItem.getNewsImg()).into(holder.imgNews);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(context,NewsDetailsActivity.class);
                newIntent.putExtra("URL",dataItem.getNewsUrl());
                context.startActivity(newIntent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return Data.size();
    }
//  First Step
//    public class NewsVH extends RecyclerView.ViewHolder {
//        public NewsVH(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
    public class NewsVH extends RecyclerView.ViewHolder {
        TextView txtTitle,txtDesc;
        ImageView imgNews;
        public NewsVH(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            imgNews = itemView.findViewById(R.id.newsImg);

        }
    }
}
