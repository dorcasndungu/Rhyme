package com.example.rhyme.network;

import com.example.rhyme.Models.TracksApiPojos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TracksAPI {
    @GET("chart.tracks.get")
    Call<TracksApiPojos> getTracks(
            @Query("apikey") String key,
            @Query("chart_name") String chart_name,
            @Query("page") int page,
            @Query("page_size") int page_size,
            @Query("country") String country,
            @Query("f_has_lyrics") int f_has_lyrics

    );

}
