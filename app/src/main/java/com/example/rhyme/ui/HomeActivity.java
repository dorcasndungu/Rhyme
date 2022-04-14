package com.example.rhyme.ui;

import static android.content.ContentValues.TAG;

import static com.example.rhyme.constants.Constants.mm_API_KEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.rhyme.Models.Track;
import com.example.rhyme.Models.TracksApiPojos;
import com.example.rhyme.R;
import com.example.rhyme.adapter.TrackListAdapter;
import com.example.rhyme.databinding.ActivityHomeBinding;
import com.example.rhyme.lyricsModels.Lyrics;
import com.example.rhyme.lyricsModels.LyricsApiPojos;
import com.example.rhyme.network.LyricsAPI;
import com.example.rhyme.network.LyricsClient;
import com.example.rhyme.network.TracksAPI;
import com.example.rhyme.network.TracksClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Track> apiTrackList;
    String apiLyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView=binding.mainRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);





    TracksAPI client = TracksClient.getClient();
    Call<TracksApiPojos> call = client.getTracks(mm_API_KEY, "hot",1,80,"xw",1);

        call.enqueue(new Callback<TracksApiPojos>() {
        @Override
        public void onResponse(Call<TracksApiPojos> call, Response<TracksApiPojos> response) {

            hideProgressBar();

            if (response.isSuccessful()) {
                apiTrackList=response.body().getMessage().getBody().getTrackList();

                adapter=new TrackListAdapter(apiTrackList,HomeActivity.this);
                recyclerView.setAdapter(adapter);

                showTracks();
            } else {
                showUnsuccessfulMessage();
            }
        }

        @Override
        public void onFailure(Call<TracksApiPojos> call, Throwable t) {
            Log.e(TAG, "onFailure: ",t );
            hideProgressBar();
            showFailureMessage();
        }

    });
}
    private void showFailureMessage() {
        binding.errorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        binding.errorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        binding.errorTextView.setText("Something went wrong. Please try again later");
        binding.errorTextView.setVisibility(View.VISIBLE);
    }

    private void showTracks() {
        binding.mainRecycler.setVisibility(View.VISIBLE);

    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }


}