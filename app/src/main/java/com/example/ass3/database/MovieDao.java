package com.example.ass3.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    void InsertMovie(Movie movie);

    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> GetAllMovies();

    @Query("UPDATE movies SET rating = :rating WHERE movieId =:id")
    void UpdateRating(int id, int rating);

    @Query("DELETE FROM movies WHERE movieId= :id")
    void RemoveMovie(int id);
}
