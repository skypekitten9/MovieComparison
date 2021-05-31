package com.example.ass3.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 2, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static MovieDatabase Instance;

    public static MovieDatabase getDatabase(final Context context)
    {
        if (Instance == null)
        {
            synchronized (MovieDatabase.class)
            {
                if(Instance== null)
                {
                    Instance = Room.databaseBuilder(context.getApplicationContext(),MovieDatabase.class,"movie_database").build();

                }
            }
        }
        return Instance;
    }

}
