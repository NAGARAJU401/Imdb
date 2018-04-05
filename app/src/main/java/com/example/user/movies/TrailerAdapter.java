package com.example.user.movies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by USER on 28-03-2018.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.MyTrailerHolder> {
    Context context;
    List<TrailerResult>trailerResultList;
    TrailerResult trailerResult;

    public TrailerAdapter(Context context, List<TrailerResult> trailerResultList) {
        this.context = context;
        this.trailerResultList = trailerResultList;
    }

    @Override
    public MyTrailerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trailers,null);
        return new MyTrailerHolder(view);
    }

    @Override
    public void onBindViewHolder(MyTrailerHolder holder, final int position) {

        String Trailerthumbnailurl="https://img.youtube.com/vi/"+trailerResultList.get(position).getKey()+"/hqdefault.jpg";
        Picasso.with(context).load(Trailerthumbnailurl).into(holder.imageView);
        holder.playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trailerurl = "https://www.youtube.com/watch?v="+trailerResultList.get(position).getKey();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailerurl));
                context.startActivity(intent);            }
        });
    }

    @Override
    public int getItemCount() {
        return trailerResultList.size();
    }
   public class MyTrailerHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;
        ImageButton playbutton;
        public MyTrailerHolder(View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardtrailer);
            imageView=itemView.findViewById(R.id.trailerimage);
            playbutton = itemView.findViewById(R.id.playbutton);

        }
}
}


