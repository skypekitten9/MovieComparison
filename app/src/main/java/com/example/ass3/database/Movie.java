package com.example.ass3.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "movies")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name="movieId")
    private int id;



    @NotNull
    @ColumnInfo(name = "title")
    private String title;

    @NotNull
    @ColumnInfo(name = "year")
    private int year;

    @NotNull
    @ColumnInfo(name = "rating")
    private float rating;

    @NotNull
    @ColumnInfo(name = "imdbId")
    private String imdbId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Movie(@NotNull String title, @NotNull int year, @NotNull float rating, @NotNull String imdbId) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.imdbId = imdbId;
    }
    public Movie() {
    }
}
