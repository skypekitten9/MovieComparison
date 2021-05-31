package com.example.ass3.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ass3.Chuck;
import com.example.ass3.Controller;
import com.example.ass3.MainActivity;
import com.example.ass3.OMDBAPI;
import com.example.ass3.OMDBResponse;
import com.example.ass3.R;
import com.example.ass3.Rw_AdapterOMDB;
import com.example.ass3.database.Movie;

import java.util.List;

public class Fragment_Imdb extends Fragment {

    private String[] titles;
    private String[] score;
    RecyclerView recyclerView;
    Button btnSearch;
    MainActivity mainActivity;
    Controller controller;
    public OMDBAPI omdbapi;
    TextView tvSearch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__imdb, container, false);
        // Inflate the layout for this fragment
        InitializeImdb(view);




        return view;
    }
    public void SetMain(MainActivity main){ this.mainActivity = main; }
    public void SetCont(Controller cont){this.controller = cont;}

    private void InitializeImdb(View view) {
        omdbapi= new OMDBAPI(mainActivity);

        recyclerView = view.findViewById(R.id.rwImdb);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Rw_AdapterOMDB adapter = new Rw_AdapterOMDB(R.layout.rw_row); //LÄGG TILL VAD SOM SKALL IN
        recyclerView.setAdapter(adapter);
        adapter.setController(controller);
//        controller.GetSearchResult().observe(this, new Observer<List<OMDBResponse>>() {
//            @Override
//            public void onChanged(List<OMDBResponse> omdbResponses) {
//                adapter.SetResult(omdbResponses);
//            }
//        });

        controller.GetSearchResult().observe(this, new Observer<List<OMDBResponse>>() {
            @Override
            public void onChanged(List<OMDBResponse> omdbResponses) {
                adapter.SetResult(omdbResponses);
            }
        });
        //btn effect

        tvSearch = view.findViewById(R.id.tvSearch);
        
        //Search button functions
        btnSearch = view.findViewById(R.id.btnChuck);
        controller.buttonEffect(btnSearch);
        btnSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(mainActivity, PopUpWindow.class ));
                omdbapi.SearchShows(tvSearch.getText().toString());
                boolean temp = true;
                controller.mediaPlayer.start();

                while(temp) {
                    if (omdbapi.getDone()) {
                        temp = false;
                        Log.d("resultat", String.valueOf(omdbapi.result2.size()));
                        adapter.SetResult(controller.GetSearchResult2());
                    }
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String value = "-1";
        Log.d("TAG", "Resultcode = " + requestCode);
        switch (resultCode)
        {
            case (Activity.RESULT_OK):
//                Log.d(TAG, "Resultcode = " + Integer.toString(resultCode));

                value = data.getStringExtra("rating");
//                Log.d(TAG, "onActivityResult: returned " + Float.toString(value) );

                Movie temp = controller.getTempMovie();

                String title = temp.getTitle();
                String year = temp.getYear();
                String rating = value;
                String imdbid = temp.getImdbId();
                controller.InsertMovie(title,year,rating,imdbid);
                break;

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        tvSearch.setText("");
    }
}
