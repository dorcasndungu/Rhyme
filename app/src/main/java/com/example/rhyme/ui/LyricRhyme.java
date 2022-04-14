package com.example.rhyme.ui;

import static android.content.ContentValues.TAG;
import static com.example.rhyme.constants.Constants.mm_API_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rhyme.Models.TracksApiPojos;
import com.example.rhyme.R;
import com.example.rhyme.databinding.ActivityLyricRhymeBinding;
import com.example.rhyme.lyricsModels.LyricsApiPojos;
import com.example.rhyme.network.LyricsAPI;
import com.example.rhyme.network.LyricsClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LyricRhyme extends AppCompatActivity {
ActivityLyricRhymeBinding binding;
String apiLyrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric_rhyme);


        Intent intent = getIntent();
        String trackPlaceholder = intent.getStringExtra("trackName");
        Toast.makeText(LyricRhyme.this,trackPlaceholder,Toast.LENGTH_SHORT).show();
//        String albumPlaceholder = intent.getStringExtra("albumName");
//        String artistPlaceholder = intent.getStringExtra("artistName");
//
//        binding.artistname.setText(artistPlaceholder);
//        binding.trackname.setText(trackPlaceholder);


//        LyricsAPI client = LyricsClient.getClient();
//        Call<LyricsApiPojos> call = client.getLyrics(mm_API_KEY, trackPlaceholder, artistPlaceholder);
//
//        call.enqueue(new Callback<LyricsApiPojos>() {
//            @Override
//            public void onResponse(Call<LyricsApiPojos> call, Response<LyricsApiPojos> response) {
//
//                hideProgressBar();
//
//                if (response.isSuccessful()) {
//                    assert response.body() != null;
//                    apiLyrics = response.body().getMessage().getBody().getLyrics().getLyricsBody();
//
//
//                    showLyrics();
//                } else {
//                    showUnsuccessfulMessage();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LyricsApiPojos> call, Throwable t) {
//                Log.e(TAG, "onFailure: ", t);
//                hideProgressBar();
//                showFailureMessage();
//            }
//
//        });
//    }
//    private void showFailureMessage() {
//        binding.errorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
//        binding.errorTextView.setVisibility(View.VISIBLE);
//    }
//
//    private void showUnsuccessfulMessage() {
//        binding.errorTextView.setText("Something went wrong. Please try again later");
//        binding.errorTextView.setVisibility(View.VISIBLE);
//    }
//
//    private void showLyrics() {
//        binding.trackname.setVisibility(View.VISIBLE);
//        binding.artistname.setVisibility(View.VISIBLE);
//        binding.button.setVisibility(View.VISIBLE);
//        binding.RhymePH.setVisibility(View.VISIBLE);
//        binding.button4.setVisibility(View.VISIBLE);
//        binding.lyricsPlaceHolder.setVisibility(View.VISIBLE);
//        binding.Share.setVisibility(View.VISIBLE);
//
//    }
//
//    private void hideProgressBar() {
//        binding.progressBar.setVisibility(View.GONE);
//    }
}}

