package com.example.user.movies;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by USER on 29-03-2018.
 */

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CrewHolder> {

    Context context;
    List<Crew> crewList;
    Crew crew;

    public CrewAdapter(Context context, List<Crew> crewList) {
        this.context = context;
        this.crewList = crewList;
    }

    @Override
    public CrewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.crewlayout,null);
        return new CrewHolder(view);
    }

    @Override
    public void onBindViewHolder(CrewHolder holder, int position) {
        String imageurl="http://image.tmdb.org/t/p/w500"+crewList.get(position).getProfilePath();
        Log.d("url",imageurl);
        Picasso.with(context).load(imageurl).into(holder.imageView);
        holder.crewcharactername.setText(crewList.get(position).getName());
        holder.crewname.setText(crewList.get(position).getJob());
    }

    @Override
    public int getItemCount() {
        return crewList.size();
    }


    public class CrewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView crewname,crewcharactername;

        public CrewHolder(View itemView) {
              super(itemView);
            imageView=itemView.findViewById(R.id.crewimage);
            crewname=itemView.findViewById(R.id.crewname);
            crewcharactername=itemView.findViewById(R.id.crewcharactername);

        }
    }
}
