package com.example.user.movies;

import com.example.user.movies.pojo.CastnCrew;
import com.example.user.movies.pojo.Main;
import com.example.user.movies.pojo.Posters;
import com.example.user.movies.pojo.Result;
import com.example.user.movies.pojo.Trailers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by USER on 26-03-2018.
 */


public interface ApiInterface {
    @GET("movie/upcoming")
    Call<Main> getUpComingMovies(@Query("api_key") String apiKey, @Query("page")Integer page);

    @GET("movie/{id}/images")
    Call<Posters>getmovieimages (@Path("id")int id, @Query("api_key")String apiKey);

    @GET("movie/{id}")
    Call<Result> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/latest")
    Call<Main>getlatestmovies(@Query("api_key")String apiKey,@Query("page")Integer paage);

    @GET("movie/popular")
    Call<Main>getpopularmovies(@Query("api_key")String apiKey,@Query("page")Integer page);

    @GET("movie/now_playing")
    Call<Main>getnowplayingmovies(@Query("api_key")String apiKey,@Query("page")Integer page);

    @GET("movie/top_rated")
    Call<Main>gettopratedmovies(@Query("api_key")String apiKey,@Query("page")Integer page);

    @GET("movie/{id}/videos")
    Call<Trailers>gettrailermovies(@Path("id")int id , @Query("api_key") String apiKey);


    @GET("movie/{id}/credits")
    Call<CastnCrew>getcastncrewdetails(@Path("id")int id , @Query("api_key")String apiKey);

}
