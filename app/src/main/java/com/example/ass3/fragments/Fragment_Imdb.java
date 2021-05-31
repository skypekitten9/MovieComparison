package com.example.ass3.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ass3.Controller;
import com.example.ass3.MainActivity;
import com.example.ass3.OMDBAPI;
import com.example.ass3.OMDBResponse;
import com.example.ass3.PopUpWindow;
import com.example.ass3.R;
import com.example.ass3.Rw_Adapter;
import com.example.ass3.Rw_AdapterOMDB;

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
    Fragment_Imdb thisFragement = this;


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
//        controller.GetSearchResult().observe(this,
//                omdbResponses -> {
//            adapter.SetResult(omdbResponses);
//        });
        controller.GetSearchResult().observe(this, new Observer<List<OMDBResponse>>() {
            @Override
            public void onChanged(List<OMDBResponse> omdbResponses) {
                adapter.SetResult(omdbResponses);
            }
        });
        //btn effect
        controller.buttonEffect(btnSearch);
        tvSearch = view.findViewById(R.id.tvSearch);
        //Search button functions
        btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(mainActivity, PopUpWindow.class ));
                omdbapi.SearchShows(tvSearch.getText().toString());

            }
        });

    }
}
