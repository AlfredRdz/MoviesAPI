package com.alfred.moviesapi;

import android.os.Bundle;

import com.alfred.moviesapi.Model.Serie;
import com.alfred.moviesapi.Model.SerieDAO;
import com.alfred.moviesapi.Model.SerieDAORespuesta;
import com.alfred.moviesapi.Model.SerieRespuesta;
import com.alfred.moviesapi.interfaces.SeriesApi;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alfred.moviesapi.databinding.ActivitySerieDetailsBinding;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SerieDetailsActivity extends AppCompatActivity {

    private ActivitySerieDetailsBinding binding;

    private static final String TAG = "Serie Detail: ";
    String id, name, overview, poster_path, first_air_date;
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySerieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;

         retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getDataIntent();
        getData();

        toolBarLayout.setTitle(name);

    }

    private void getDataIntent() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
            Toast.makeText(SerieDetailsActivity.this, "id: " + id, Toast.LENGTH_SHORT).show();
//            name = getIntent().getStringExtra("name");
//            overview = getIntent().getStringExtra("overview");
//            poster_path = getIntent().getStringExtra("poster_path");
//            first_air_date = getIntent().getStringExtra("first_air_date");
//
//            binding.txtFirtsAir.setText(first_air_date);
//            binding.txtOver.setText(overview);
//
//            Glide.with(getApplicationContext())
//                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + poster_path)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(binding.imageSerie);
        }
    }

    public void getData() {
        SeriesApi seriesApi = retrofit.create(SeriesApi.class);

        Call<SerieDAO> respuestaCall = seriesApi.getSerieById(Integer.parseInt(id));

        respuestaCall.enqueue(new Callback<SerieDAO>() {
            @Override
            public void onResponse(Call<SerieDAO> call, Response<SerieDAO> response) {
                if (response.isSuccessful()) {
                    SerieDAO serieRespuesta = response.body();
                    int id = serieRespuesta.getId();
                    name = serieRespuesta.getName();
                    String overview = serieRespuesta.getOverview();
                    String poster_path = serieRespuesta.getPoster_path();
                    String first_air_date = serieRespuesta.getFirst_air_date();
                    Integer number_of_episodes = serieRespuesta.getNumber_of_episodes();
                    Integer number_of_seasons = serieRespuesta.getNumber_of_seasons();



                    Log.e(TAG, "Serie: " + id + name + overview + poster_path + first_air_date + number_of_episodes + number_of_seasons);
                    binding.txtRealeseSerie.setText(first_air_date);
                    binding.txtEpisodesSerie.setText(String.valueOf(number_of_episodes));
                    binding.txtTemporadaSerie.setText(String.valueOf(number_of_seasons));
                    binding.txtOverviewSerie.setText(overview);

                    Glide.with(getApplicationContext())
                            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + poster_path)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(binding.imageSerie);
                } else {
                    Log.e(TAG, "Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<SerieDAO> call, Throwable t) {
                Log.e(TAG, "Serie: " + t.getMessage());
            }
        });

    }
}