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

import com.alfred.moviesapi.Model.Search;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    List<Search> searchList;
    Activity activity;
    String title, release;

    public SearchAdapter(List<Search> searchList, Activity activity) {
        this.searchList = searchList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.cardmovie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Search search = searchList.get(position);

        Glide.with(activity)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + search.getPoster_path())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);

        if (search.getTitle() != null) {
            holder.textView.setText(search.getTitle());
            title = search.getTitle();
        } else {
            holder.textView.setText(search.getName());
            title = search.getName();
        }

        if (search.getRelease_date() != null) {
            release = search.getRelease_date();
        } else {
            release = search.getFirst_air_date();
        }

        holder.card_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), search.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, SearchDetailsActivity.class);
                intent.putExtra("id", String.valueOf(search.getId()));
                intent.putExtra("title", title);
                intent.putExtra("overview", search.getOverview());
                intent.putExtra("media_type", search.getMedia_type());
                intent.putExtra("poster_path", search.getPoster_path());
                intent.putExtra("release_date", release);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        CardView card_movie;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            card_movie = itemView.findViewById(R.id.card_movie);
        }
    }
}
