package com.example.user.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
//here i have created main class
//public keyword is used in the declaration of a class,method or field;public classes,method and fields can be accessed by the members of any class.
//extends is for extending a class. implements is for implementing an interface
//AppCompatActivity is a parent class

SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<Result> results;
    Result result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Variables, methods, and constructors, which are declared protected in a superclass can be accessed only by the subclasses
        // in other package or any class within the package of the protected members class.
        //void is a Java keyword.  Used at method declaration and definition to specify that the method does not return any type,
        // the method returns void.
        //onCreate Called when the activity is first created. This is where you should do all of your normal static set up: create views,
        // bind data to lists, etc. This method also provides you with a Bundle containing the activity's previously frozen state,
        // if there was one.Always followed by onStart().
        //Bundle is most often used for passing data through various Activities.

        super.onCreate(savedInstanceState);
        // call the super class onCreate to complete the creation of activity like the view hierarchy

        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //  main is the xml you have created under res->layout->main.xml
        //  Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        // The other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        // the design

        results=new ArrayList<>();
        Log.d("Oncreate","oncreate executed");

        recyclerView=findViewById(R.id.upcomingrv);
        //declaring id"s for recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
       myAdapter= new MyAdapter( MainActivity.this,results);
        recyclerView.setAdapter(myAdapter);
        // Here we set the  adapter to our recyclerview
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<Main>getupcomingmoviescall=apiService.getUpComingMovies("f47dd4de64c6ef630c2b0d50a087cc33",1);
        getupcomingmoviescall.enqueue(new Callback<Main>() {
            @Override
            public void onResponse(Call<Main> call, Response<Main> response) {

                Log.d("upcoimgmovies",response.toString());
                for(int i=0;i<response.body().getResults().size();i++){
                    result = new Result();
                    result.setStatus(response.body().getResults().get(i).getStatus());

                        result.setTitle(response.body().getResults().get(i).getTitle());
                        result.setReleaseDate(response.body().getResults().get(i).getReleaseDate());
                        result.setId(response.body().getResults().get(i).getId());
                        result.setVoteAverage(response.body().getResults().get(i).getVoteAverage());
                        result.setPosterPath(response.body().getResults().get(i).getPosterPath());

                            results.add(result);


                }
myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Main> call, Throwable t) {
                Toast.makeText(MainActivity.this, "request failed", Toast.LENGTH_SHORT).show();
                Log.d("throwable",t.toString());

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Latest_Movies) {
            Intent intent=new Intent(MainActivity.this,LatestMovies.class);
            startActivity(intent);

            return true;

        }else if (id == R.id.Most_Popular){
            Intent  intent=new Intent(MainActivity.this,Popular.class);
            //fetching the pos it starts the activty using startActivty()

            startActivity(intent);


        }else if (id == R.id.Now_Playing){
            Intent  intent=new Intent(MainActivity.this,Now_Playing.class);
            startActivity(intent);


        }else if (id == R.id.Top_Rated){
            Intent  intent=new Intent(MainActivity.this,Top_Rated.class);
            startActivity(intent);

        }else if (id==R.id.fave){
            Intent intent =new Intent(MainActivity.this,FavoriteMovies.class);
            startActivity(intent);
        }else if (id==R.id.wlmv){
            Intent intent =new Intent(MainActivity.this,WatchListmovies.class);
            startActivity(intent);

        }else if (id ==R.id.refresh){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
