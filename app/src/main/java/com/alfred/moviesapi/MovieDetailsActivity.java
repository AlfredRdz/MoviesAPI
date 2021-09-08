package com.alfred.moviesapi;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import com.alfred.moviesapi.databinding.ActivityMovieDetailsBinding;

import java.util.Objects;

public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailsBinding binding;

    String id, title, overview, release_date, poster_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;


        getDataintent();
        toolBarLayout.setTitle(title);
    }

    private void getDataintent(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title")) {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            overview = getIntent().getStringExtra("overview");
            release_date = getIntent().getStringExtra("release_date");
            poster_path = getIntent().getStringExtra("poster_path");

            binding.txtLanzamiento.setText(release_date);
            binding.txtOverview.setText(overview);

            Glide.with(getApplicationContext()).load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + poster_path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imageMovie);

        }
    }
}