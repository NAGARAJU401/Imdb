package com.example.user.movies;

import android.view.View;

/**
 * Created by USER on 27-03-2018.
 */

public  interface ClickListener{
    public void onClick(View view, int position);
    public void onLongClick(View view,int position);
}