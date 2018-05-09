package com.example.user.movies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.user.movies.pojo.Result;

import java.util.ArrayList;
import java.util.List;

public class WatchListmovies extends AppCompatActivity {
    RecyclerView recyclerViewwl;
    watchlistAdapter watchlistAdapterwl;
    List<Result>results;
    WatchlistDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_listmovies);
        recyclerViewwl=findViewById(R.id.watchlistmv);
        results=new ArrayList<>();
        database=new WatchlistDatabase(WatchListmovies.this);
        results=database.getwatchlistfromdb();
        watchlistAdapterwl=new watchlistAdapter(WatchListmovies.this,results);
        recyclerViewwl.setLayoutManager(new GridLayoutManager(this,3));
        recyclerViewwl.addItemDecoration(new SpacesItemDecoration(6));
        recyclerViewwl.setAdapter(watchlistAdapterwl);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
