package com.example.rhyme.ui;

import static android.content.ContentValues.TAG;
import static com.example.rhyme.constants.Constants.mm_API_KEY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import com.example.rhyme.R;


import com.example.rhyme.constants.Constants;
import com.example.rhyme.databinding.ActivityHomeBinding;
import com.example.rhyme.databinding.ActivityLyricRhymeBinding;
import com.example.rhyme.lyricsModels.LyricsApiPojos;
import com.example.rhyme.lyricsModels.MyRhyme;
import com.example.rhyme.network.LyricsAPI;
import com.example.rhyme.network.LyricsClient;
import com.example.rhyme.network.LyricsAPI;
import com.example.rhyme.network.LyricsClient;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.BuildConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LyricRhyme extends AppCompatActivity implements View.OnClickListener{
ActivityLyricRhymeBinding binding;
private String apiLyrics;
    private String Artist;
    private String Track;
    private String savedRhyme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLyricRhymeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Artist= getIntent().getStringExtra("artistName");
        Track= getIntent().getStringExtra("trackName");
        Toast.makeText(LyricRhyme.this, Artist+" "+ Track, Toast.LENGTH_SHORT).show();
       binding.artistname.setText(Artist);
        binding.trackname.setText(Track);
        binding.Share.setOnClickListener(this);
        binding.Save.setOnClickListener(this);
        binding.savedlist.setOnClickListener(this);




                LyricsAPI client = LyricsClient.getClient();
        Call<LyricsApiPojos> call = client.getLyrics(mm_API_KEY, Track,Artist);

        call.enqueue(new Callback<LyricsApiPojos>() {
            @Override
            public void onResponse(Call<LyricsApiPojos> call, Response<LyricsApiPojos> response) {

                hideProgressBar();

                if (response.isSuccessful()) {
                    apiLyrics=response.body().getMessage().getBody().getLyrics().getLyricsBody();

          binding.lyricsPlaceHolder.setText(apiLyrics);
                    showLyrics();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<LyricsApiPojos> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();
            }

        });
        binding.RhymePH.setMovementMethod(new ScrollingMovementMethod());
        binding.lyricsPlaceHolder.setMovementMethod(new ScrollingMovementMethod());
    }
    private void showFailureMessage() {
        binding.errorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        binding.errorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        binding.errorTextView.setText("Something went wrong. Please try again later");
        binding.errorTextView.setVisibility(View.VISIBLE);
    }

    private void showLyrics() {
        binding.lyricsPlaceHolder.setVisibility(View.VISIBLE);

    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View view) {
if(view==binding.Share){
    savedRhyme=binding.RhymePH.getText().toString();
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_TEXT,
            "Hey check out my "+Track+" version: "+savedRhyme   + BuildConfig.APPLICATION_ID);
    sendIntent.setType("text/plain");
    startActivity(sendIntent);
    Toast.makeText(LyricRhyme.this, "Sharing...", Toast.LENGTH_SHORT).show();
}else if(view==binding.Save){
    MyRhyme newRhyme=new MyRhyme(Track,binding.RhymePH.getText().toString());
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    DatabaseReference rhymesRef = FirebaseDatabase
            .getInstance()
            .getReference(Constants.FIREBASE_CHILD_RHYMES)
            .child(uid);

    DatabaseReference pushRef = rhymesRef.push();
    String pushId = pushRef.getKey();
    newRhyme.setPushId(pushId);
    pushRef.setValue(newRhyme);

    Intent intent = new Intent(LyricRhyme.this, SavedRhymesActivity.class);
    Toast.makeText(LyricRhyme.this, "Saved", Toast.LENGTH_SHORT).show();
    startActivity(intent);
}
else if(view==binding.savedlist){
    Intent intent = new Intent(LyricRhyme.this, SavedRhymesActivity.class);
    Toast.makeText(LyricRhyme.this, "List of rhymes", Toast.LENGTH_SHORT).show();
    startActivity(intent);
}
    }

}




