package com.alfred.moviesapi;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alfred.moviesapi.Model.Serie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.MyViewHolder> {
    List<Serie> serieList;
    Activity activity;

    public SerieAdapter(Activity activity, List<Serie> serieList) {
        this.activity = activity;
        this.serieList = serieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardmovie, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Serie serie = serieList.get(position);

        Glide.with(activity).load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + serie.getPoster_path())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
        holder.textView.setText(serie.getName());

        holder.card_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SerieDetailsActivity.class);
                intent.putExtra("id", String.valueOf(serie.getId()));
                intent.putExtra("name", serie.getName());
                intent.putExtra("overview", serie.getOverview());
                intent.putExtra("poster_path", serie.getPoster_path());
                intent.putExtra("first_air_date", serie.getFirst_air_date());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return serieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CardView card_movie;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            card_movie = itemView.findViewById(R.id.card_movie);
        }
    }
}
