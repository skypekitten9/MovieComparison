package com.example.ass3.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ass3.Chuck;
import com.example.ass3.Controller;
import com.example.ass3.R;


public class Fragment_Home extends Fragment {

    private Button btnChuck;
    private Controller controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment

        btnChuck = view.findViewById(R.id.btnChuck);

        btnChuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chuck c = new Chuck(controller);
                c.StartConnect();
            }
        });

        return view;
    }

    public void SetController(Controller controller) {
        this.controller = controller;
    }

}