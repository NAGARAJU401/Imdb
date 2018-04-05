package com.example.user.movies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by USER on 26-03-2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Myviewholder> {

    Context context;
    List<Result>resultslist;
    Result result;

    public MyAdapter(Context context, List<Result> resultslist) {
        this.context = context;
        this.resultslist = resultslist;
    }

    @Override
    public Myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movielayout,null);


        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(Myviewholder holder, int position) {
        holder.title.setText(resultslist.get(position).getTitle());
        holder.releasedate.setText(resultslist.get(position).getReleaseDate());
        holder.ratingBar.setRating(resultslist.get(position).getVoteAverage()/2);
        String imageurl="http://image.tmdb.org/t/p/w500"+resultslist.get(position).getPosterPath();
        //here it holds the image urls that we need to have and get those posters
        Picasso.with(context).load(imageurl).placeholder(R.drawable.placeholder).into(holder.imageView);
        //Picasso will holds the images in the given code and loads them


    }

    @Override
    public int getItemCount() {
        return resultslist.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title,releasedate;
        CardView cardView;
        RatingBar ratingBar;
        public Myviewholder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.poster);
            title=itemView.findViewById(R.id.movietitle);
            cardView=itemView.findViewById(R.id.card_view);
            releasedate=itemView.findViewById(R.id.releasedate);
            ratingBar=itemView.findViewById(R.id.ratingbar);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,Movie_Details.class);
                    intent.putExtra("id",resultslist.get(getAdapterPosition()).getId());

                    context.startActivity(intent);
                }
            });
        }
    }
}
