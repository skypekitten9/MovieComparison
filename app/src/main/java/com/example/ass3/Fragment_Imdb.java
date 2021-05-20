package com.example.ass3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Imdb extends Fragment {

    private String[] titles;
    private String[] score;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__imdb, container, false);
        // Inflate the layout for this fragment
        InitializeImdb(view);
        return view;
    }

    private void InitializeImdb(View view) {
        recyclerView = view.findViewById(R.id.rwImdb);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.Adapter adapter = new Rw_Adapter(); //LÄGG TILL VAD SOM SKALL IN
        recyclerView.setAdapter(adapter);
    }
}