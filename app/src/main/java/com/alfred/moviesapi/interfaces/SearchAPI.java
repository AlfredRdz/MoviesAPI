package com.alfred.moviesapi.interfaces;

import com.alfred.moviesapi.Model.SearchRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchAPI {
    @GET("search/multi")
    Call<SearchRespuesta> searchByName(@Query("api_key") String apiKey,
                                       @Query("language") String language,
                                       @Query("query") String query,
                                       @Query("page") int pageIndex);
}
