package com.example.user.movies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMovies extends AppCompatActivity {
RecyclerView recyclerViewfv;
FavoriteAdapter favoriteAdapter;
List<Result> results;
Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);
        recyclerViewfv =findViewById(R.id.favoritemv);
        results=new ArrayList<>();
        database=new Database(FavoriteMovies.this);
        results=database.getmoviedetailsfromdb();
        favoriteAdapter=new FavoriteAdapter(FavoriteMovies.this,results);
        recyclerViewfv.setLayoutManager(new GridLayoutManager(this,3));
        recyclerViewfv.addItemDecoration(new SpacesItemDecoration(6));
        recyclerViewfv.setAdapter(favoriteAdapter);
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
