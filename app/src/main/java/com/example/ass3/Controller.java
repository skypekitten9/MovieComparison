package com.example.ass3;

import android.app.Activity;
import android.content.Intent;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;

import com.example.ass3.database.Movie;
import com.example.ass3.database.MovieRepository;
import com.example.ass3.fragments.Fragment_Favourites;
import com.example.ass3.fragments.Fragment_Home;
import com.example.ass3.fragments.Fragment_Imdb;
import com.example.ass3.fragments.Fragment_Rotten;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class Controller {
    MainActivity main;
    Fragment_Home homeFragment;
    Fragment_Favourites favouritesFragment;
    Fragment_Imdb imdbFragment;
    Fragment_Rotten rottenFragment;

    BottomNavigationView bottomNavigationView;

    FragmentManager fragmentManager;

    MovieRepository movieRepository;

    Movie tempMovie;


    public Controller(MainActivity mainActivity, BottomNavigationView bottomNav){
        this.main = mainActivity;

        //Fragments
        homeFragment = new Fragment_Home();
        favouritesFragment = new Fragment_Favourites();
        favouritesFragment.setController(this);
        imdbFragment = new Fragment_Imdb();
        imdbFragment.SetCont(this);
        imdbFragment.SetMain(mainActivity);
        rottenFragment = new Fragment_Rotten();
        rottenFragment.setController(this);
        //BottomNavigation
        bottomNavigationView = bottomNav;
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);
        //FragmentManager
        fragmentManager = main.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        main.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

        //Database
        movieRepository = new MovieRepository(mainActivity.getApplication());
    }

    //Bottom navigation change fragment method
    public BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = homeFragment;
                    break;
                case R.id.nav_imdb:
                    selectedFragment = imdbFragment;
                    break;
                case R.id.nav_rotten:
                    selectedFragment = rottenFragment;
                    break;
                case R.id.nav_fav:
                    selectedFragment = favouritesFragment;
                    break;
            }
            main.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    public void InsertMovie(String title, int year, float rating, String imdbid){
        Movie movie = new Movie(title,year,rating,imdbid);
        movieRepository.InsertMovie(movie);
    }

    public LiveData<List<Movie>> GetMovies()
    {
        return movieRepository.GetAllMovies();
    }

    public void RemoveMovie(int id)
    {
        movieRepository.RemoveMovie(id);
    }

    public void OpenPopup()
    {
        rottenFragment.startActivityForResult(new Intent(main, PopUpWindow.class ),50);
    }

    public void MovieToAdd(String title, int year, String imdbId )
    {
        tempMovie= new Movie();
        tempMovie.setTitle( title);
        tempMovie.setYear(year);
        tempMovie.setImdbId(imdbId);

    }

    public Movie getTempMovie() {
        return tempMovie;
    }

    public void ShowToast(TemplateResponse resp)
    {
        main.runOnUiThread(new Toasters(main,resp));
    }



}

class Toasters implements Runnable
{
    MainActivity main;
    TemplateResponse resp;

    public Toasters(MainActivity main, TemplateResponse resp) {
        this.main = main;
        this.resp = resp;
    }

    @Override
    public void run() {
        Toast.makeText(main,resp.getValue().getJoke(),Toast.LENGTH_LONG).show();
    }
}