package com.example.rhyme.network;

import com.example.rhyme.Models.TracksApiPojos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchAPI {
    @GET("track.search")
    Call<TracksApiPojos> getTracks(
            @Query("apikey") String key,
            @Query("q") String query,
            @Query("page") int page,
            @Query("page_size") int page_size,
            @Query("s_track_rating") String s_track_rating,
            @Query("f_has_lyrics") int f_has_lyrics

  );
}
