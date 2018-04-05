package com.example.user.movies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 02-04-2018.
 */

public class WatchlistDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VRESION = 6;

    public static final String DATABASE_NAME = "watchlist.db";

    public static final String TABLE_NAME = "watchlistMovies";
    public static final String COLUMN_ID = "id";
    public static final String MOVIE_ID = "movie_id";
    public static final String MOVIE_NAME = "movie_name";
    public static final String COLUMN_IS_WATCHLIST="watchlist";
    private static final String COLUMN_POSTER_PATH = "poster_path";
    private Context context;

    public WatchlistDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VRESION);
        this.context = context;

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + MOVIE_ID + " INTEGER," + MOVIE_NAME + " TEXT," + COLUMN_POSTER_PATH + " TEXT," + COLUMN_IS_WATCHLIST + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public  void addwatchlist(int id,String title,String poster_path ,String status){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(MOVIE_ID,id );
        values.put(MOVIE_NAME,title);
        values.put(COLUMN_POSTER_PATH,poster_path);
        values.put(COLUMN_IS_WATCHLIST,status);
        long result = sqLiteDatabase.insert(TABLE_NAME,null,values);
        if (result== -1){
            Toast.makeText(context, "not inserted", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context, "add to watchlist", Toast.LENGTH_SHORT).show();
        }
        sqLiteDatabase.close();
    }


    public List<Result> getwatchlistfromdb() {
        List<Result> movieidslist = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT* FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(MOVIE_ID));
                String title = cursor.getString(cursor.getColumnIndex(MOVIE_NAME));
                String poster_path = cursor.getString(cursor.getColumnIndex(COLUMN_POSTER_PATH));
                Result result = new Result();
                result.setId(id);
                result.setTitle(title);
                result.setPosterPath(poster_path);
                movieidslist.add(result);
                cursor.moveToNext();

            }
            cursor.close();
        }
        return movieidslist;
    }

    public void removeFromFav(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME, MOVIE_ID + "=?", new String[]{String.valueOf(id)});
        database.close();
    }

    public boolean isMovieFavourite(int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, MOVIE_ID + "=" + id, null, null, null, null);
        if (cursor.getCount() > 0) {
            database.close();
            return true;

        }
        return false;
    }
    }

