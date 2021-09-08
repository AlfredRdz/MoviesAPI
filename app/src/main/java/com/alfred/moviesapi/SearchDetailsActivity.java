package com.alfred.moviesapi;

import android.os.Bundle;

import com.alfred.moviesapi.databinding.ActivitySearchDetailsBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;



import java.util.Objects;

public class SearchDetailsActivity extends AppCompatActivity {

    private ActivitySearchDetailsBinding binding;

    String id, title, overview, media_type, poster_path, release_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySearchDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;


        getDataIntent();
        toolBarLayout.setTitle(title);
    }

    private void getDataIntent() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title")) {

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            overview = getIntent().getStringExtra("overview");
            media_type = getIntent().getStringExtra("media_type");
            poster_path = getIntent().getStringExtra("poster_path");
            release_date = getIntent().getStringExtra("release_date");

            binding.txtOverviewSearch.setText(overview);
            binding.txtTipo.setText(media_type);
            binding.txtRealese.setText(release_date);

            Glide.with(getApplicationContext())
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + poster_path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imageSearch);

        }
    }

}