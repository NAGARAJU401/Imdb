package com.example.user.movies;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 28-03-2018.
 */

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.MyHolder> {
Context context;
List<Poster>posterList;
Poster poster;



    public PosterAdapter(Context context, List<Poster> posterList) {
        this.context = context;
        this.posterList = posterList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.posters,null);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String imageurl="http://image.tmdb.org/t/p/w500"+posterList.get(position).getFilePath();
        Picasso.with(context).load(imageurl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;
        public MyHolder(View itemView) {
            super(itemView);


            cardView =itemView.findViewById(R.id.card);
            imageView=itemView.findViewById(R.id.pic);

        }

    }
}

