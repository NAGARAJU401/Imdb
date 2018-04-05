package com.example.user.movies;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 05-04-2018.
 */

public class watchlistAdapter extends RecyclerView.Adapter<watchlistAdapter.MyviewHolder> {
    Context context;
    List<Result> watchlistmovies = new ArrayList<>();
    Result result;

    public  watchlistAdapter(Context context,List<Result>watchlistmovies){
        this.context=context;
        this.watchlistmovies=watchlistmovies;


    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view= LayoutInflater.from(parent.getContext()).inflate(R.layout.watchlistlayout,null);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        holder.title.setText(watchlistmovies.get(position).getTitle());


        String imageurl="http://image.tmdb.org/t/p/w500"+watchlistmovies.get(position).getPosterPath();
        //here it holds the image urls that we need to have and get those posters
        Picasso.with(context).load(imageurl).placeholder(R.drawable.placeholder).into(holder.imageView);
        //Picasso will holds the images in the given code and loads them

    }

    @Override
    public int getItemCount() {
        return watchlistmovies.size();
    }


    public class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        CardView cardView;
        public MyviewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.watchposter);
            title=itemView.findViewById(R.id.watchtext);
            cardView=itemView.findViewById(R.id.watchcv);

        }
    }}