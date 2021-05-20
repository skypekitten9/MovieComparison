package com.example.ass3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Rw_Adapter extends RecyclerView.Adapter<Rw_Adapter.CustomViewHolder> {
    @NonNull
    @Override
    public Rw_Adapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rw_row,parent,false);
        return new Rw_Adapter.CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Rw_Adapter.CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
