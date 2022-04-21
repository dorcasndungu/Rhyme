package com.example.rhyme.ui;

import static android.content.ContentValues.TAG;

import static com.example.rhyme.constants.Constants.mm_API_KEY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.rhyme.Models.Track;
import com.example.rhyme.Models.TracksApiPojos;
import com.example.rhyme.R;
import com.example.rhyme.adapter.TrackListAdapter;
import com.example.rhyme.constants.Constants;
import com.example.rhyme.databinding.ActivityHomeBinding;
import com.example.rhyme.lyricsModels.Lyrics;
import com.example.rhyme.lyricsModels.LyricsApiPojos;
import com.example.rhyme.network.LyricsAPI;
import com.example.rhyme.network.LyricsClient;

import com.example.rhyme.network.SearchAPI;
import com.example.rhyme.network.SearchClient;
import com.example.rhyme.network.TracksAPI;
import com.example.rhyme.network.TracksClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Track> apiTrackList;
    String apiLyrics;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = binding.mainRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        binding.savedRhymes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, SavedRhymesActivity.class);
               
                startActivity(intent);


            }
        });
        binding.topTracks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchTopTracks();
            }
        });
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentSearch = mSharedPreferences.getString(Constants.PREFERENCES_SEARCHQUERY_KEY, null);
            fetchQueryTracks(mRecentSearch);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                    Toast.makeText(HomeActivity.this, "Welcome, " + user.getDisplayName() + "!", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String queryTrack) {
                addToSharedPreferences(queryTrack);
                fetchQueryTracks(queryTrack);
                return false;
            }

         


            @Override
            public boolean onQueryTextChange(String queryTrack) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }
    private void fetchQueryTracks(String queryTrack) {
        SearchAPI client = SearchClient.getClient();
        Call<TracksApiPojos> call = client.getTracks(mm_API_KEY, queryTrack,1,80,"desc",1);
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

    private void fetchTopTracks(){
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
        binding.embarrassing.setVisibility(View.GONE);
    }
    private void addToSharedPreferences(String searchQuery) {
        mEditor.putString(Constants.PREFERENCES_SEARCHQUERY_KEY, searchQuery).apply();
    }

}