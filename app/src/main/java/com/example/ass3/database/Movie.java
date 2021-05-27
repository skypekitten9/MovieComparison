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
    private String year;

    @NotNull
    @ColumnInfo(name = "rating")
    private String rating;

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Movie(@NotNull String title, @NotNull String year, @NotNull String rating, @NotNull String imdbId) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.imdbId = imdbId;
    }
    public Movie() {
    }
}
