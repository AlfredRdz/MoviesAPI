package com.alfred.moviesapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alfred.moviesapi.Model.Search;
import com.alfred.moviesapi.Model.SearchRespuesta;
import com.alfred.moviesapi.interfaces.SearchAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BusquedaActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity2: ";

    RecyclerView recyclerViewBusqueda;

    String busqueda;
    List<Search> list = new ArrayList<>();

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        recyclerViewBusqueda = findViewById(R.id.recyclerViewBusqueda);

        recyclerViewBusqueda.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewBusqueda.setHasFixedSize(true);

        ActionBar actionBar = getSupportActionBar();


        SearchAdapter searchAdapter = new SearchAdapter(list, BusquedaActivity.this);

        getData();
        actionBar.setTitle(busqueda);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        SearchAPI searchAPI = retrofit.create(SearchAPI.class);

        Call<SearchRespuesta> respuestaCall = searchAPI.searchByName("b3f8890e1932e1c0987ddc09ac0d9a96", "es", busqueda, 1);

        respuestaCall.enqueue(new Callback<SearchRespuesta>() {
            @Override
            public void onResponse(Call<SearchRespuesta> call, Response<SearchRespuesta> response) {
                if (response.isSuccessful()) {
                    SearchRespuesta searchRespuesta = response.body();
                    List<Search> searchList = searchRespuesta.getResults();

                    for (int i = 0; i < searchList.size(); i++) {
                        Search search = searchList.get(i);
                        Log.e(TAG, "Busqueda: " + search.getTitle());
                        list.add(search);
                    }
                    recyclerViewBusqueda.setAdapter(searchAdapter);
                }
            }

            @Override
            public void onFailure(Call<SearchRespuesta> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
    }

    private void getData() {
        if (getIntent().hasExtra("busqueda")) {
            busqueda = getIntent().getStringExtra("busqueda");
        }
    }
}