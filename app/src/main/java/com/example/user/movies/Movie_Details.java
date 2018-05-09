package com.example.user.movies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.movies.pojo.Cast;
import com.example.user.movies.pojo.CastnCrew;
import com.example.user.movies.pojo.Crew;
import com.example.user.movies.pojo.Poster;
import com.example.user.movies.pojo.Posters;
import com.example.user.movies.pojo.Result;
import com.example.user.movies.pojo.Trailers;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import at.blogc.android.views.ExpandableTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movie_Details extends AppCompatActivity {
ImageView imageView,watchlist;
ImageView favorites;
TextView title,release,budget,revenue,status,voteaverage,overview;
RatingBar ratingBar;
RecyclerView recyclerView,trailersrv,castrv,crewrv;
PosterAdapter posterAdapter;
TrailerAdapter trailerAdapter;
List<TrailerResult>trailerResultList;
List<Poster>posterList;
List<Cast>castList;
List<Crew>crewList;
Crew crew;
Cast cast;
CastAdapter castAdapter;
CrewAdapter crewAdapter;
Poster poster;
TrailerResult trailerResult;
Database database;
WatchlistDatabase watchlistDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__details);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        posterList=new ArrayList<>();
        trailerResultList=new ArrayList<>();
        castList=new ArrayList<>();
        crewList=new ArrayList<>();

        imageView=findViewById(R.id.Poster);
        title=findViewById(R.id.Title);
        release=findViewById(R.id.Release);
        budget=findViewById(R.id.budget);
        revenue=findViewById(R.id.revenue);
        status=findViewById(R.id.status);
        ratingBar=findViewById(R.id.rating);
        voteaverage=findViewById(R.id.voteaverage);
        overview=findViewById(R.id.overview);
        favorites=findViewById(R.id.favorites);
        watchlist=findViewById(R.id.watchlist);
        recyclerView=findViewById(R.id.detailposter);
        watchlistDatabase = new WatchlistDatabase(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(Movie_Details.this,LinearLayoutManager.HORIZONTAL,false));
        posterAdapter=new PosterAdapter(Movie_Details.this,posterList);
        recyclerView.addItemDecoration(new SpacesItemDecoration(15));
        recyclerView.setAdapter(posterAdapter);



        trailersrv=findViewById(R.id.trailersrv);
        trailersrv.setLayoutManager(new LinearLayoutManager(Movie_Details.this,LinearLayoutManager.HORIZONTAL,false));
        trailersrv.addItemDecoration(new SpacesItemDecoration(10));
        trailerAdapter=new TrailerAdapter(Movie_Details.this,trailerResultList);
        trailersrv.setAdapter(trailerAdapter);

        castrv=findViewById(R.id.cast);
        castrv.setLayoutManager(new LinearLayoutManager(Movie_Details.this,LinearLayoutManager.HORIZONTAL,false));
        castrv.addItemDecoration(new SpacesItemDecoration(15));
        castAdapter=new CastAdapter(Movie_Details.this,castList);
        castrv.setAdapter(castAdapter);

        crewrv=findViewById(R.id.crew);
        crewrv.setLayoutManager(new LinearLayoutManager(Movie_Details.this,LinearLayoutManager.HORIZONTAL,false));
        crewrv.addItemDecoration(new SpacesItemDecoration(15));
        crewAdapter=new CrewAdapter(Movie_Details.this,crewList);
        crewrv.setAdapter(crewAdapter);

        final int id=getIntent().getExtras().getInt("id");
        database=new Database(Movie_Details.this);
        if(!database.isMovieFavourite(id)){
            favorites.setTag("Not_fav");
            favorites.setImageResource(R.drawable.unfavorite);
        }else if(database.isMovieFavourite(id)){
            favorites.setTag("fav");
            favorites.setImageResource(R.drawable.favotite);
        }

        if(!watchlistDatabase.isMovieFavourite(id)){
            watchlist.setTag("not_inWatchlist");
            watchlist.setImageResource(R.drawable.watchlist_disable_normal);
        }else if(watchlistDatabase.isMovieFavourite(id)){
            watchlist.setTag("inwatchlist");
            watchlist.setImageResource(R.drawable.watchlist_enable_normal);
        }

        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<Result>getmoviedetailscall=apiService.getMovieDetails(id,"f47dd4de64c6ef630c2b0d50a087cc33");
        getmoviedetailscall.enqueue(new Callback<Result>()

        {

            @Override
            public void onResponse(Call<Result> call, final Response<Result> response) {
                Log.d("RESPONSE",response.toString());
                Result result=new Result();

                title.setText(response.body().getTitle());
                budget.setText("budget:");
                budget.append(String.valueOf(response.body().getBudget()));
                revenue.setText("Revenue: ");
                revenue.append(String.valueOf(response.body().getRevenue()));
              status.setText("status:");
              status.append(response.body().getStatus());
              release.setText("Release Date:");
              release.append(response.body().getReleaseDate());

               ratingBar.setRating(response.body().getVoteAverage()/2);
                final String imageurl="http://image.tmdb.org/t/p/w500"+response.body().getPosterPath();
                Picasso.with(Movie_Details.this).load(imageurl).into(imageView);
                voteaverage.setText(response.body().getVoteAverage()+"/10"+" voted by "+response.body().getVoteCount()+" users");
                overview.setText(response.body().getOverview());
                final ExpandableTextView expandableTextView = findViewById(R.id.overview);
                final ImageButton buttonToggle = findViewById(R.id.button_toggle);

// set animation duration via code, but preferable in your layout files by using the animation_duration attribute
                expandableTextView.setAnimationDuration(750L);

                // set interpolators for both expanding and collapsing animations
                expandableTextView.setInterpolator(new OvershootInterpolator());

// or set them separately
                expandableTextView.setExpandInterpolator(new OvershootInterpolator());
                expandableTextView.setCollapseInterpolator(new OvershootInterpolator());
                expandableTextView.setText(response.body().getOverview());

// toggle the ExpandableTextView

// but, you can also do the checks yourself
                buttonToggle.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(final View v)
                    {
                        if (expandableTextView.isExpanded())
                        {

                            buttonToggle.setImageResource(R.drawable.button);
                            expandableTextView.collapse();


                        }
                        else
                        {
                            buttonToggle.setImageResource(R.drawable.up);
                            expandableTextView.expand();

                        }
                    }
                });

// listen for expand / collapse events
               /* expandableTextView.setOnExpandListener(new ExpandableTextView.OnExpandListener()
                {
                    @Override
                    public void onExpand(final ExpandableTextView view)
                    {

                    }

                    @Override
                    public void onCollapse(final ExpandableTextView view)
                    }
                });
*/  favorites.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(favorites.getTag()=="fav"){
                            database.removeFromFav(id);
                            Toast.makeText(Movie_Details.this, "removed from favourites", Toast.LENGTH_SHORT).show();
                            favorites.setImageResource(R.drawable.unfavorite);
                            favorites.setTag("Not_fav");
                        }else if(favorites.getTag()=="Not_fav"){
                            database.addmovie(id,response.body().getTitle(),response.body().getPosterPath());
                            favorites.setImageResource(R.drawable.favotite);
                            favorites.setTag("fav");
                        }
                    }
                });
                watchlist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(watchlist.getTag()=="inwatchlist"){
                            watchlistDatabase.removeFromFav(id);
                            Toast.makeText(Movie_Details.this, "removed from watchlist", Toast.LENGTH_SHORT).show();
                            watchlist.setImageResource(R.drawable.watchlist_disable_normal);
                            watchlist.setTag("not_inWatchlist");
                        }else if(watchlist.getTag()=="not_inWatchlist"){
                            watchlistDatabase.addwatchlist(id,response.body().getTitle(),response.body().getPosterPath(),"YES");
                            watchlist.setImageResource(R.drawable.watchlist_enable_normal);
                            watchlist.setTag("inwatchlist");
                        }

                    }
                });
            }


            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

        Call<Posters>getmovieimagescall=apiService.getmovieimages(id,"f47dd4de64c6ef630c2b0d50a087cc33");
        getmovieimagescall.enqueue(new Callback<Posters>() {
            @Override
            public void onResponse(Call<Posters> call, Response<Posters> response) {

                Log.d("RESPONSE",response.toString());
                for (int i=0; i<response.body().getPosters().size(); i++ ){

                    poster=new Poster();
                    poster.setFilePath(response.body().getPosters().get(i).getFilePath());
                    posterList.add(poster);
                }
                posterAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Posters> call, Throwable t) {

            }
        });
        Call<Trailers>getmovietrailerscall=apiService.gettrailermovies(id,"f47dd4de64c6ef630c2b0d50a087cc33");
       getmovietrailerscall.enqueue(new Callback<Trailers>() {
           @Override
           public void onResponse(Call<Trailers> call, Response<Trailers> response) {
               Log.d("RESPONCE",response.toString());
               for (int i=0; i<response.body().getResults().size();i++){
                   trailerResult=new TrailerResult();
                   trailerResult.setKey(response.body().getResults().get(i).getKey());
                   trailerResultList.add(trailerResult);
               }
               trailerAdapter.notifyDataSetChanged();
           }

           @Override
           public void onFailure(Call<Trailers> call, Throwable t) {

           }

       });
       Call<CastnCrew>getcastncredetails=apiService.getcastncrewdetails(id,"f47dd4de64c6ef630c2b0d50a087cc33");
       getcastncredetails.enqueue(new Callback<CastnCrew>() {
           @Override
           public void onResponse(Call<CastnCrew> call, Response<CastnCrew> response) {
              Log.d("RESPONCE",response.toString());
               for (int i=0; i<response.body().getCast().size();i++){
                   cast=new Cast();
                   cast.setCharacter(response.body().getCast().get(i).getCharacter());
                   cast.setName(response.body().getCast().get(i).getName());
                   cast.setProfilePath(response.body().getCast().get(i).getProfilePath());
                   castList.add(cast);
               }
               castAdapter.notifyDataSetChanged();
           }

           @Override
           public void onFailure(Call<CastnCrew> call, Throwable t) {

           }
       });
       Call<CastnCrew>getcrewdetails=apiService.getcastncrewdetails(id,"f47dd4de64c6ef630c2b0d50a087cc33");
       getcrewdetails.enqueue(new Callback<CastnCrew>() {
           @Override
           public void onResponse(Call<CastnCrew> call, Response<CastnCrew> response) {
              for (int i=0;i<response.body().getCrew().size();i++){
                  crew=new Crew();
                  crew.setName(response.body().getCrew().get(i).getName());
                  crew.setJob(response.body().getCrew().get(i).getJob());
                  crew.setProfilePath(response.body().getCrew().get(i).getProfilePath());
                  crewList.add(crew);
                     }
               crewAdapter.notifyDataSetChanged();
           }

           @Override
           public void onFailure(Call<CastnCrew> call, Throwable t) {

           }
       });




        Log.d("id",String.valueOf(id));
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
