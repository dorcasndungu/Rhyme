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
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhyme.R;
import com.example.rhyme.adapter.FirebaseMyRhymeViewHolder;

import com.example.rhyme.constants.Constants;
import com.example.rhyme.databinding.ActivityHomeBinding;
import com.example.rhyme.databinding.ActivityLyricRhymeBinding;
import com.example.rhyme.databinding.ActivitySavedrhymeBinding;
import com.example.rhyme.lyricsModels.MyRhyme;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class SavedRhymesActivity extends AppCompatActivity {
    private DatabaseReference rhymesReference;

    private FirebaseRecyclerAdapter<MyRhyme, FirebaseMyRhymeViewHolder> mFirebaseAdapter;
    private FirebaseRecyclerOptions<MyRhyme> options;
ActivitySavedrhymeBinding binding;
private List<MyRhyme> myRhymeList;
  private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySavedrhymeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String uid =user.getUid();
        rhymesReference= FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RHYMES).child(uid);
        recyclerView=binding.RecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        showRhymes();
        hideProgressBar();

        binding.RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        options=new  FirebaseRecyclerOptions.Builder<MyRhyme>().setQuery(rhymesReference,MyRhyme.class).build();
        mFirebaseAdapter=new FirebaseRecyclerAdapter<MyRhyme, FirebaseMyRhymeViewHolder>(options) {
            @Override
            public void onBindViewHolder(@NonNull FirebaseMyRhymeViewHolder holder, int position, @NonNull MyRhyme model) {
                String myRhymeTitle=model.getTitle();
                String myRhymeRhyme=model.getRhyme();
                String myRhymePushId=model.getPushId();
               holder.itemTitle.setText(model.getTitle());
               holder.itemRhyme.setText(model.getRhyme());
               if (position<0){
                   showNoSavedRhyme();
               }
               holder.itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       AppCompatActivity activity=(AppCompatActivity)view.getContext();
                RhymeFragment RhymeFragment=new RhymeFragment();
               activity.getSupportFragmentManager().beginTransaction().replace(R.id.savedList,RhymeFragment).addToBackStack(null).commit();

                Bundle bundle = new Bundle();
                bundle.putString("positionRhyme",model.getRhyme());
                bundle.putString("positionTitle",model.getTitle());
                bundle.putString("positionPushId",model.getPushId());

                RhymeFragment.setArguments(bundle);

                   }
               });
            }

            @NonNull
            @Override
            public FirebaseMyRhymeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
              View v=  LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
                return new FirebaseMyRhymeViewHolder(v);

            }
        };
        showRhymes();
        hideProgressBar();
        mFirebaseAdapter.startListening();
        recyclerView.setAdapter(mFirebaseAdapter);



    }
    private void showRhymes() {
        binding.RecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
        binding.embarrassing.setVisibility(View.GONE);
    }
private void showNoSavedRhyme(){
        binding.notSaved.setVisibility(View.VISIBLE);
    Toast.makeText(SavedRhymesActivity.this, "No saved rhymes yet.",
            Toast.LENGTH_LONG).show();
}

}
