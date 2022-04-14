package com.example.rhyme.network;

import com.example.rhyme.lyricsModels.LyricsApiPojos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LyricsAPI {
    @GET("matcher.lyrics.get")
    Call<LyricsApiPojos> getLyrics(
            @Query("apikey") String key,
            @Query("q_track") String q_track_name,
            @Query("q_artist") String q_artist_name

    );

}
