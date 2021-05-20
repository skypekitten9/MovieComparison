package com.example.ass3.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ass3.Controller;
import com.example.ass3.R;
import com.example.ass3.Rw_Adapter;
import com.example.ass3.Rw_FavAdapter;

public class Fragment_Favourites extends Fragment {

    RecyclerView recyclerView;
    Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

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
        Rw_FavAdapter adapter = new Rw_FavAdapter(R.layout.rw_rowfavorite); //LÄGG TILL VAD SOM SKALL IN
        adapter.setController(controller);
        recyclerView.setAdapter(adapter);
        controller.GetMovies().observe(this, movies -> adapter.SetMovies(movies));

    }
}