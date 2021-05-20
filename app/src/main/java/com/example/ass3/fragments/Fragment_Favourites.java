package com.example.ass3.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ass3.R;
import com.example.ass3.Rw_Adapter;

public class Fragment_Favourites extends Fragment {

    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__favourites, container, false);
        // Inflate the layout for this fragment
        InitializeFavourites(view);
        return view;
    }

    private void InitializeFavourites(View view) {
        recyclerView = view.findViewById(R.id.rwFavourites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.Adapter adapter = new Rw_Adapter(); //LÄGG TILL VAD SOM SKALL IN
        recyclerView.setAdapter(adapter);
    }
}