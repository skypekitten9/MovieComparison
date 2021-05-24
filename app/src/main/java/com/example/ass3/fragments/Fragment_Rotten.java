package com.example.ass3.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RatingBar;

import com.example.ass3.Chuck;
import com.example.ass3.Controller;
import com.example.ass3.PopUpWindow;
import com.example.ass3.R;
import com.example.ass3.Rw_Adapter;
import com.example.ass3.database.Movie;

public class Fragment_Rotten extends Fragment {
    private static final String TAG = "Fragment_Rotten";
    private String[] titles;
    private String[] score;
    RecyclerView recyclerView;
    private Button btn;
    private Controller controller;
    private PopupWindow popupWindow;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__rotten, container, false);
        // Inflate the layout for this fragment
        InitializeRotten(view);
        return view;
    }

    private void InitializeRotten(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rwRotten);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        Rw_Adapter adapter = new Rw_Adapter(R.layout.rw_row); //LÄGG TILL VAD SOM SKALL IN
        adapter.setController(controller);
        recyclerView.setAdapter(adapter);
        controller.GetMovies().observe(this, movies -> adapter.SetMovies(movies));
        btn = view.findViewById(R.id.refreshbtn);
        popupWindow = new PopupWindow();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(mainActivity, PopUpWindow.class ));

                controller.InsertMovie("Test",2002,1.5f,"aibsdbasdh");
                Chuck c = new Chuck();
                c.StartConnect();

            }
        });

    }

    public void InsertMovie(float rating)
    {
        String title = "Hej";
        int year = 1990;
        String imdbid = "123uh12bn4i1bn4";
        controller.InsertMovie(title,year,rating,imdbid);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        float value = -1f;
        switch (requestCode)
        {
            case (50):
                if (resultCode == Activity.RESULT_OK)
                    value= data.getFloatExtra("rating", -1);
                    Log.d(TAG, "onActivityResult: returned " + Float.toString(value) );

                    Movie temp = controller.getTempMovie();

                    String title = temp.getTitle() +"kopia";
                    int year = temp.getYear();
                    float rating =value;
                    String imdbid = temp.getImdbId();
                    controller.InsertMovie(title,year,rating,imdbid);
                    break;

        }
    }
}