package com.example.ass3.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieRepository {

    private MovieDao dao;

    private LiveData<List<Movie>> allMovies;

    public MovieRepository(Application application) {
        MovieDatabase db = MovieDatabase.getDatabase( application);
        dao = db.movieDao();
        allMovies = dao.GetAllMovies();
    }

    public void InsertMovie(Movie movie)
    {
        Thread t1 = new Thread(new InsertMovie(dao,movie));
        t1.start();
       // Runnable task = new InsertMovie(dao,movie);
        //task.run();
    }

    public void RemoveMovie(int id)
    {
        Thread t1 = new Thread( new RemoveMovie(dao, id));
        t1.start();
    }

    public LiveData<List<Movie>> GetAllMovies()
    {
        // Thread t1 = new Thread(new GetAllMovies(dao,this));
        //t1.start();
        return allMovies;
    }

    private static class GetAllMovies implements Runnable
    {
        private MovieDao dao;
        private MovieRepository repo;

        public GetAllMovies(MovieDao dao, MovieRepository repo) {
            this.dao = dao;
            this.repo = repo;
        }

        @Override
        public void run() {
            repo.allMovies = dao.GetAllMovies();
        }
    }

    private static class InsertMovie implements Runnable
    {
        private MovieDao dao;
        private Movie movie;

        public InsertMovie(MovieDao dao, Movie movie) {
            this.dao = dao;
            this.movie = movie;
        }

        @Override
        public void run() {
            dao.InsertMovie(movie);
        }
    }

    private static class RemoveMovie implements Runnable {
        private MovieDao dao;
        private int id;
        public RemoveMovie(MovieDao dao, int id) {
            this.dao = dao;
            this.id = id;
        }

        @Override
        public void run() {
            dao.RemoveMovie(id);

        }
    }
}
