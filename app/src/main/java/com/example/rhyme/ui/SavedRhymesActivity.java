package com.example.rhyme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rhyme.R;
import com.example.rhyme.adapter.FirebaseMyRhymeViewHolder;
import com.example.rhyme.adapter.FirebaseRecyclerViewAdapter;
import com.example.rhyme.constants.Constants;
import com.example.rhyme.databinding.ActivityHomeBinding;
import com.example.rhyme.databinding.ActivityLyricRhymeBinding;
import com.example.rhyme.databinding.ActivitySavedrhymeBinding;
import com.example.rhyme.lyricsModels.MyRhyme;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class SavedRhymesActivity extends AppCompatActivity {
    private DatabaseReference rhymesReference;
    private FirebaseRecyclerViewAdapter firebaseRecyclerViewAdapter;
    private FirebaseRecyclerAdapter<MyRhyme, FirebaseMyRhymeViewHolder> mFirebaseAdapter;
ActivitySavedrhymeBinding binding;
private List<MyRhyme> myRhymeList;
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySavedrhymeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RHYMES);
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                myRhymeList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    MyRhyme myRhyme = postSnapshot.getValue(MyRhyme.class);
                    myRhymeList.add(myRhyme);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


    });
        showRhymes();
        hideProgressBar();
        firebaseRecyclerViewAdapter=new FirebaseRecyclerViewAdapter(myRhymeList,SavedRhymesActivity.this);
        binding.RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.RecyclerView.setAdapter(firebaseRecyclerViewAdapter);
    }


    private void showRhymes() {
        binding.RecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }
}
