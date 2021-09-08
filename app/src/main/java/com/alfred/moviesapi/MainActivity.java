package com.alfred.moviesapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView;

import android.widget.TextView;
import android.widget.Toast;

import com.alfred.moviesapi.Model.Movie;
import com.alfred.moviesapi.Model.MovieRespuesta;
import com.alfred.moviesapi.Model.Search;
import com.alfred.moviesapi.Model.Serie;
import com.alfred.moviesapi.Model.SerieRespuesta;
import com.alfred.moviesapi.interfaces.MovieAPI;
import com.alfred.moviesapi.interfaces.SeriesApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity: ";

    RecyclerView recyclerView, recyclerView2;
    TextView verPelicula, verSerie;

    MovieAdapter movieAdapter;
    SerieAdapter serieAdapter;

    List<Movie> movieList = new ArrayList<>();
    List<Serie> serieList = new ArrayList<>();
    List<Search> searchList = new ArrayList<>();

    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView2 = findViewById(R.id.recyclerView2);
        verPelicula = findViewById(R.id.verPelicula);
        verSerie = findViewById(R.id.verSerie);

//        verPelicula.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Movies.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        verSerie.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Series.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getDataMovies();
        getDataSeries();
    }

    private void getDataMovies() {


        MovieAPI movieAPI = retrofit.create(MovieAPI.class);

        Call<MovieRespuesta> llamada = movieAPI.allMovies();

        llamada.enqueue(new Callback<MovieRespuesta>() {
            @Override
            public void onResponse(Call<MovieRespuesta> call, Response<MovieRespuesta> response) {
                if (response.isSuccessful()) {
                    MovieRespuesta movieRespuesta = response.body();
                    List<Movie> movies = movieRespuesta.getResults();

                    for (int i = 0; i< movies.size(); i++) {
                        Movie m = movies.get(i);
                        movieList.add(m);
                        //Log.e(TAG, "Pelicula: " + m.getTitle());
                        //Toast.makeText(getApplicationContext(), "Pelicula: " + m.getTitle(), Toast.LENGTH_SHORT).show();
                    }

                    movieAdapter = new MovieAdapter(MainActivity.this, movieList);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

                    recyclerView.setAdapter(movieAdapter);
                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                    Toast.makeText(getApplicationContext(), "Error: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieRespuesta> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), "Fallo: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataSeries() {
        SeriesApi seriesApi = retrofit.create(SeriesApi.class);

        Call<SerieRespuesta> respuestaCall = seriesApi.allSeries();

        respuestaCall.enqueue(new Callback<SerieRespuesta>() {
            @Override
            public void onResponse(Call<SerieRespuesta> call, Response<SerieRespuesta> response) {
                if (response.isSuccessful()) {
                    SerieRespuesta serieRespuesta = response.body();
                    List<Serie> list = serieRespuesta.getResults();

                    for (int i = 0; i < list.size(); i++) {
                        Serie serie = list.get(i);
                        serieList.add(serie);
                        //Log.e(TAG, "Serie: " + serie.getName());
                    }

                    serieAdapter = new SerieAdapter(MainActivity.this, serieList);
                    recyclerView2.setHasFixedSize(true);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

                    recyclerView2.setAdapter(serieAdapter);
                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                    Toast.makeText(getApplicationContext(), "Error: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SerieRespuesta> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), "Fallo: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData(String nombre){
        Intent intent = new Intent(MainActivity.this, BusquedaActivity.class);
        intent.putExtra("busqueda", nombre);
        startActivity(intent);
//        SearchAPI searchAPI = retrofit.create(SearchAPI.class);
//
//        Call<SearchRespuesta> searchCall = searchAPI.searchByName("b3f8890e1932e1c0987ddc09ac0d9a96", "es", nombre, 1);
//
//        searchCall.enqueue(new Callback<SearchRespuesta>() {
//            @Override
//            public void onResponse(Call<SearchRespuesta> call, Response<SearchRespuesta> response) {
//                if (response.isSuccessful()) {
//                    SearchRespuesta searchRespuesta = response.body();
//                    List<Search> list = searchRespuesta.getResults();
//
//                    for (int i = 0; i < list.size(); i++) {
//                        Search search = list.get(i);
//                        Log.e(TAG, "Busqueda: " + search.getTitle());
//
//                        searchList.add(search);
//                    }
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "Error " + response.errorBody(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SearchRespuesta> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //getData(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}