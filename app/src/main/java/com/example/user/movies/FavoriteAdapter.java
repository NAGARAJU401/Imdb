package com.example.user.movies;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.movies.pojo.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 05-04-2018.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewholder> {
    Context context;
    List<Result>favoriteresultList = new ArrayList<>();
    Result result;

    public FavoriteAdapter(Context context,List<Result>favoriteresultList){
        this.context=context;
        this.favoriteresultList=favoriteresultList;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View  view= LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite,null);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        holder.title.setText(favoriteresultList.get(position).getTitle());

               String imageurl="http://image.tmdb.org/t/p/w500"+favoriteresultList.get(position).getPosterPath();
        //here it holds the image urls that we need to have and get those posters
        Picasso.with(context).load(imageurl).placeholder(R.drawable.placeholder).into(holder.imageView);
        //Picasso will holds the images in the given code and loads them


    }

    @Override
    public int getItemCount() {

        return favoriteresultList.size();
    }


    public class MyViewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        CardView cardView;
    public MyViewholder(View itemView) {

        super(itemView);
        imageView=itemView.findViewById(R.id.favposter);
        title=itemView.findViewById(R.id.favtext);

        cardView=itemView.findViewById(R.id.favoritecv);
          }
}
}


