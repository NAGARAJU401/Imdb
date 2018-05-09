package com.example.user.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.movies.pojo.Cast;
import com.example.user.movies.pojo.CastnCrew;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by USER on 29-03-2018.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastnCrewholder> {
Context context;
List<Cast>castList;
CastnCrew castnCrew;

    public CastAdapter(Context context, List<Cast> castList) {
        this.context = context;
        this.castList = castList;
    }

    @Override
    public CastnCrewholder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.castncrewlayout,null);
        return new CastnCrewholder(view);
    }

    @Override
    public void onBindViewHolder(CastnCrewholder holder, int position) {

        String imageurl="http://image.tmdb.org/t/p/w500"+castList.get(position).getProfilePath();
        Picasso.with(context).load(imageurl).into(holder.imageView);
        holder.charactername.setText(castList.get(position).getCharacter());
        holder.originalname.setText(castList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public class CastnCrewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView charactername,originalname;
        public CastnCrewholder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.castimage);
            charactername=itemView.findViewById(R.id.charactername);
            originalname=itemView.findViewById(R.id.origanlname);
        }
    }

}
