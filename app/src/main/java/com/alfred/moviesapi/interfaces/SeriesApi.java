package com.alfred.moviesapi.interfaces;

import com.alfred.moviesapi.Model.SerieDAO;
import com.alfred.moviesapi.Model.SerieDAORespuesta;
import com.alfred.moviesapi.Model.SerieRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SeriesApi {
    @GET("tv/popular?api_key=b3f8890e1932e1c0987ddc09ac0d9a96&language=es&page=1")
    Call<SerieRespuesta> allSeries();

    @GET("tv/{id}?api_key=b3f8890e1932e1c0987ddc09ac0d9a96&language=es")
    Call<SerieDAO> getSerieById(@Path("id") int id);
}
