package com.example.ass3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass3.database.Movie;

import java.util.List;

public class Rw_FavAdapter extends RecyclerView.Adapter<Rw_FavAdapter.CustomViewHolder> {

    private int itemLayout;
    private List<Movie> movies;
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Rw_FavAdapter(int itemLayout) {
        this.itemLayout = itemLayout;
    }

    public void SetMovies(List<Movie> movies)
    {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Rw_FavAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new Rw_FavAdapter.CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Rw_FavAdapter.CustomViewHolder holder, int position) {

        holder.twTitle.setText(movies.get(position).getTitle());
        holder.twYear.setText(Integer.toString(movies.get(position).getYear()));
        holder.twRating.setText(Float.toString(movies.get(position).getRating()));
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.RemoveMovie(movies.get(position).getId());

            }
        });

    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView twTitle;
        private TextView twYear;
        private TextView twRating;
        private Button btnRemove;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            twTitle = itemView.findViewById(R.id.twTitle);
            twYear = itemView.findViewById(R.id.twYear);
            twRating = itemView.findViewById(R.id.twRating);
            btnRemove = itemView.findViewById(R.id.rwButtonRemove);
        }
    }
}
