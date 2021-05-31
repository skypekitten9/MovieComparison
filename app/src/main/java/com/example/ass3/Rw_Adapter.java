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

public class Rw_Adapter extends RecyclerView.Adapter<Rw_Adapter.CustomViewHolder> {

    private int itemLayout;
    private List<Movie> movies;
    private Controller controller;
    private MainActivity main;

    public void setMain(MainActivity main) {
        this.main = main;
    }

    public void setController(Controller controller) {
        this.controller = controller;
        setMain(controller.main);
    }

    public Rw_Adapter(int itemLayout) {
        this.itemLayout = itemLayout;
    }

    public void SetMovies(List<Movie> movies)
    {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Rw_Adapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new Rw_Adapter.CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Rw_Adapter.CustomViewHolder holder, int position) {

        holder.twTitle.setText(movies.get(position).getTitle());
        holder.twYear.setText(movies.get(position).getYear());
        holder.twRating.setText(movies.get(position).getRating());
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.OpenPopup();


                String title = movies.get(position).getTitle() +"kopia";
                String year = "2000";
                String imdbid = "123uh12bn4i1bn4";
                controller.MovieToAdd(title,year,imdbid);

               /* main.startActivity(new Intent(main, PopupWindow.class));
                String title = movies.get(position).getTitle() +"kopia";
                int year = 2000;
                float rating = 3f;
                String imdbid = "123uh12bn4i1bn4";
                controller.InsertMovie(title,year,rating,imdbid);
                */
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
        private Button btnAdd;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            twTitle = itemView.findViewById(R.id.twTitle);
            twYear = itemView.findViewById(R.id.twYear);
            twRating = itemView.findViewById(R.id.twRating);
            btnAdd = itemView.findViewById(R.id.rwButtonAdd);
        }
    }
}
