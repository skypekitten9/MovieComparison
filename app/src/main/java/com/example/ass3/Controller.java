package com.example.ass3;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Controller {
    MainActivity main;
    Fragment_Home homeFragment;
    Fragment_Favourites favouritesFragment;
    Fragment_Imdb imdbFragment;
    Fragment_Rotten rottenFragment;

    BottomNavigationView bottomNavigationView;

    FragmentManager fragmentManager;

    public Controller(MainActivity mainActivity, BottomNavigationView bottomNav){
        this.main = mainActivity;

        //Fragments
        homeFragment = new Fragment_Home();
        favouritesFragment = new Fragment_Favourites();
        imdbFragment = new Fragment_Imdb();
        rottenFragment = new Fragment_Rotten();
        //BottomNavigation
        bottomNavigationView = bottomNav;
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);
        //FragmentManager
        fragmentManager = main.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        main.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
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

}
