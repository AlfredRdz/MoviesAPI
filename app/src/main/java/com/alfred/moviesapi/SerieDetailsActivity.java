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

import com.alfred.moviesapi.databinding.ActivitySerieDetailsBinding;

import java.util.Objects;

public class SerieDetailsActivity extends AppCompatActivity {

    private ActivitySerieDetailsBinding binding;

    String id, name, overview, poster_path, first_air_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySerieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;

        getDataIntent();
        toolBarLayout.setTitle(name);

    }

    private void getDataIntent() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            overview = getIntent().getStringExtra("overview");
            poster_path = getIntent().getStringExtra("poster_path");
            first_air_date = getIntent().getStringExtra("first_air_date");

            binding.txtFirtsAir.setText(first_air_date);
            binding.txtOver.setText(overview);

            Glide.with(getApplicationContext())
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + poster_path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imageSerie);
        }
    }
}