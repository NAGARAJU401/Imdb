package com.example.user.movies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Popular extends AppCompatActivity {
RecyclerView recyclerView;
MyAdapter adapter;
List<Result>results;
Result result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most__popular);
        results=new ArrayList<>();

        recyclerView=findViewById(R.id.most_popular);
        recyclerView.setLayoutManager(new LinearLayoutManager(Popular.this,LinearLayoutManager.VERTICAL,false));
        adapter=new MyAdapter(Popular.this,results);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(Popular.this,DividerItemDecoration.VERTICAL));
        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
       Call<Main>getpopularmoviescall=apiService.getpopularmovies("f47dd4de64c6ef630c2b0d50a087cc33",1);
       getpopularmoviescall.enqueue(new Callback<Main>() {
           @Override
           public void onResponse(Call<Main> call, Response<Main> response) {
               Log.d("RESPONSE",response.toString());
               for (int i=0;i<response.body().getResults().size();i++){
                   result=new Result();
                   result.setTitle(response.body().getResults().get(i).getTitle());
                   result.setId(response.body().getResults().get(i).getId());
                   result.setReleaseDate(response.body().getResults().get(i).getReleaseDate());
                   result.setVoteAverage(response.body().getResults().get(i).getVoteAverage());
                   result.setPosterPath(response.body().getResults().get(i).getPosterPath());
                   results.add(result);
               }
               adapter.notifyDataSetChanged();
           }

           @Override
           public void onFailure(Call<Main> call, Throwable t) {
               Toast.makeText(Popular.this, "request failed", Toast.LENGTH_SHORT).show();
               Log.d("throwable",t.toString());

           }
       });



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
