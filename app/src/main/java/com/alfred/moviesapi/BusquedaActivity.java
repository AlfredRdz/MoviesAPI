package com.alfred.moviesapi;

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


    }

    private void getData() {
        if (getIntent().hasExtra("busqueda")) {
            busqueda = getIntent().getStringExtra("busqueda");
            Toast.makeText(getApplicationContext(), busqueda, Toast.LENGTH_SHORT).show();
        }
    }
}