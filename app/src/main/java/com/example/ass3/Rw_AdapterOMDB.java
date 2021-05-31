package com.example.ass3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass3.database.Movie;

import java.util.List;

public class Rw_AdapterOMDB extends RecyclerView.Adapter<Rw_AdapterOMDB.CustomViewHolder> {

    private int itemLayout;
    private List<OMDBResponse> result;
    private Controller controller;
    private MainActivity main;

    public void setMain(MainActivity main) {
        this.main = main;
    }

    public void setController(Controller controller) {
        this.controller = controller;
        setMain(controller.main);
    }

    public Rw_AdapterOMDB(int itemLayout) {
        this.itemLayout = itemLayout;
    }

    public void SetResult(List<OMDBResponse> result)
    {
        this.result = result;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Rw_AdapterOMDB.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new Rw_AdapterOMDB.CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Rw_AdapterOMDB.CustomViewHolder holder, int position) {

        holder.twTitle.setText(result.get(position).getTitle());
        holder.twYear.setText(result.get(position).getYear());
        holder.twRating.setText((result.get(position).getImdbRating()));
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.OpenPopup();

                String title = result.get(position).getTitle();
                String year = result.get(position).getYear();
                String imdbid = result.get(position).getImdbID();
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
        return result == null ? 0 : result.size();
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
