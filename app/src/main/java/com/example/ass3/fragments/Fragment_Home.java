package com.example.ass3.fragments;

import android.media.MediaPlayer;
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
    private MediaPlayer chuckPlayer;
    public int[] sounds = new int[] {R.raw.nicemeetingyou, R.raw.hahahaha, R.raw.hello, R.raw.hipartner, R.raw.yourein};
    int sound = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment

        btnChuck = view.findViewById(R.id.btnChuck);
        controller.buttonEffect(btnChuck);
        btnChuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chuck c = new Chuck(controller);
                c.StartConnect();
                chuckPlayer = MediaPlayer.create(getContext(),sounds[sound]);
                chuckPlayer.setVolume(1f,1f);
                chuckPlayer.start();
                if (sound == 4) {
                    sound = 0;
                }
                else {
                    sound++;
                }
            }
        });

        return view;
    }

    public void SetController(Controller controller) {
        this.controller = controller;
    }

}